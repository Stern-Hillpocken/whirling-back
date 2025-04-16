package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.enums.Ingredients;
import fr.poutchinystudio.whirling_back.enums.Phases;
import fr.poutchinystudio.whirling_back.user.User;
import fr.poutchinystudio.whirling_back.user.UserRepository;
import fr.poutchinystudio.whirling_back.util.PlayingArea;
import fr.poutchinystudio.whirling_back.util.RECIPES;
import fr.poutchinystudio.whirling_back.util.Recipe;
import fr.poutchinystudio.whirling_back.util.Utils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    private String id;
    private String password;
    private String ownerId;
    private long date;
    private boolean isStarted;
    private List<String> playersId;
    private Phases currentPhase;
    private List<Boolean> areReady;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PlayingArea> playingAreas;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe> deck;

    public Game(String id, String password, String ownerId) {
        this.id = id;
        this.password = password;
        this.ownerId = ownerId;
        this.date = System.currentTimeMillis();
        this.isStarted = false;
        this.playersId = new ArrayList<>();
        this.currentPhase = Phases.CHECK_FOR_WINNERS;
        this.areReady = new ArrayList<>();
        this.playingAreas = new ArrayList<>();
        this.deck = new ArrayList<>();
        refillDeck();
    }

    public void addPlayer(String playerId) {
        playersId.add(playerId);
        areReady.add(false);
        playingAreas.add(new PlayingArea());
    }

    private String ownerName() {
        UserRepository userRepository = null;
        Optional<User> optionalUser = userRepository.findById(ownerId);
        return optionalUser.get().getName();
    }

    private List<String> playersName() {
        UserRepository userRepository = null;
        List<String> pNames = null;
        for (String pId : playersId) {
            pNames.add(userRepository.findById(pId).get().getName());
        }
        return pNames;
    }

    public void setReadyFor(int userIndex) {
        areReady.set(userIndex, true);
    }

    public void cleanAreReady() {
        areReady.replaceAll(element -> false);
    }

    public void goToRecipePhase() {
        currentPhase = Phases.PLAY_RECIPES;
        draftHands();
        refillHands();
    }

    private void draftHands() {
        // Pass to left so set i from right (i+1)
        List<Recipe> firstHand = playingAreas.get(0).getHand();
        for (int i = 0; i < playersId.size(); i++) {
            if (i == playersId.size()-1) playingAreas.get(0).setHand(firstHand);
            else playingAreas.get(i).setHand(playingAreas.get(i+1).getHand());
        }
    }

    public void goToProducePhase() {
        currentPhase = Phases.PRODUCE_INGREDIENTS;
    }

    private void refillDeck() {
        deck = RECIPES.all;
    }

    public void refillHands() {
        for (PlayingArea pA : playingAreas) {
            while (pA.getHand().size() < 4) {
                List<Recipe> currentHand = pA.getHand();
                int randomIndex = Utils.random(0, deck.size()-1);
                currentHand.add(deck.get(randomIndex));
                pA.setHand(currentHand);
                deck.remove(randomIndex);

                if (deck.size() == 0) refillDeck();
            }
        }
    }

    public void applySkillsPrepared() {
        for (int index = 0; index < playingAreas.size(); index++) {
            // Generate real input and output
            List<List<Ingredients>> realInputOutput = playingAreas.get(index).realInputOutput();
            List<Ingredients> realInput = realInputOutput.get(0);
            List<Ingredients> realOutput = realInputOutput.get(1);
            int indexRightPlayer = index+1;
            if (index+1 == playingAreas.size()) indexRightPlayer = 0;
            // Remove input
            for (Ingredients i : realInput) playingAreas.get(index).getWorkbench().remove(i);
            // Add output
            for (Ingredients i : realOutput) playingAreas.get(indexRightPlayer).getWorkbench().add(i);
        }
        // Add to circle
        for (int i = 0; i < playingAreas.size(); i++) {
            //
        }
    }
}

package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.enums.Phases;
import fr.poutchinystudio.whirling_back.user.User;
import fr.poutchinystudio.whirling_back.user.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    public Game(String id, String password, String ownerId) {
        this.id = id;
        this.password = password;
        this.ownerId = ownerId;
        this.date = System.currentTimeMillis();
        this.isStarted = false;
        this.playersId = new ArrayList<>();
        this.currentPhase = Phases.CHECK_FOR_WINNERS;
        this.areReady = new ArrayList<>();
    }

    public void addPlayer(String playerId) {
        playersId.add(playerId);
        areReady.add(false);
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
    }

    public void goToProducePhase() {
        currentPhase = Phases.PRODUCE_INGREDIENTS;
    }
}

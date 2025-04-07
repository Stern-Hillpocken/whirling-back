package fr.poutchinystudio.whirling_back.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.poutchinystudio.whirling_back.enums.Ingredients;
import fr.poutchinystudio.whirling_back.game.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlayingArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public Long id;
    @ManyToOne
    @JsonIgnore
    private Game game;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Witch> witch;
    private List<Ingredients> circle;
    private List<Ingredients> workbench;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe> hand;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe> skills;
    private List<Boolean> skillsUsed;

    public void setupWitches() {
        int choiceA = Utils.random(1, WITCHES.all.size()-1);
        int choiceB = 0;
        do {
            choiceB = Utils.random(1, WITCHES.all.size()-1);
        } while (choiceB == choiceA);
        witch = List.of(WITCHES.all.get(0), WITCHES.all.get(choiceA), WITCHES.all.get(choiceB));
    }

    public void setupWitch(int witchIndex) {
        witch = new ArrayList<>(List.of(witch.get(witchIndex)));
    }

    public void setupIngredients() {
        workbench = new ArrayList<>(witch.get(0).getIngredientsLetters());
    }

    public void chooseRecipe(Recipe recipe) {
        skills.add(recipe);
        hand.remove(indexRecipeInHand(recipe));
    }

    public boolean isRecipeInHand(Recipe givenRecipe) {
        return indexRecipeInHand(givenRecipe) == -1 ? false : true;
    }

    private int indexRecipeInHand(Recipe givenRecipe) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getArcana().equals(givenRecipe.getArcana()) &&
                    hand.get(i).getInput().equals(givenRecipe.getInput()) &&
                    hand.get(i).getDirection().equals(givenRecipe.getDirection()) &&
                    hand.get(i).getOutput().equals(givenRecipe.getOutput())
            ) return i;
            else if (hand.get(i).getArcana().equals(givenRecipe.getArcana()) &&
                    hand.get(i).getInput().equals(givenRecipe.getOutput()) &&
                    hand.get(i).getDirection().equals(givenRecipe.getDirection()) &&
                    hand.get(i).getOutput().equals(givenRecipe.getInput())
            ) return i;
        }
        return -1;
    }
}

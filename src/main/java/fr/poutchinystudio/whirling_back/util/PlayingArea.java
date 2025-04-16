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
    @Column(length = 510)
    private List<Ingredients> workbench;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe> hand;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe> skills;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe> skillsPrepared;

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
        hand.remove(indexRecipeInHand(recipe));
        recipe.fixDirection();
        skills.add(recipe);
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

    public List<List<Ingredients>> realInputOutput() {
        List<Ingredients> in = new ArrayList<>();
        List<Ingredients> out = new ArrayList<>();
        List<Ingredients> workingWorkbench = new ArrayList<>(workbench);
        List<Recipe> skillsAvailable = new ArrayList<>(skills);
        for (Recipe r : skillsPrepared) {
            // Check if skill is available
            int indexSkill = indexOfSameSkill(r, skillsAvailable);
            if (indexSkill != -1) skillsAvailable.remove(indexSkill);
            else return null;
            // Merge input
            for (Ingredients i : r.getInput()) {
                if (workingWorkbench.contains(i)) workingWorkbench.remove(i);
                else if (out.contains(i)) out.remove(i);
                else return null;
                in.add(i);
            }
            //Merge output
            for (Ingredients i : r.getOutput()) out.add(i);
        }
        return new ArrayList<>(List.of(in, out));
    }

    private int indexOfSameSkill(Recipe recipe, List<Recipe> all) {
        for (int iAll = 0; iAll < all.size(); iAll++) {
            if (recipe.getInput().size() != all.get(iAll).getInput().size()) continue;
            if (recipe.getOutput().size() != all.get(iAll).getOutput().size()) continue;
            boolean haveDifference = false;
            for (int iRecipe = 0; iRecipe < all.get(iAll).getInput().size(); iRecipe++) {
                if (!isGivenIngredientInterceptRecipeIngredient(recipe.getInput().get(iRecipe), all.get(iAll).getInput().get(iRecipe))) {haveDifference = true; break;}
            }
            if (haveDifference) continue;
            for (int iRecipe = 0; iRecipe < all.get(iAll).getOutput().size(); iRecipe++) {
                if (!isGivenIngredientInterceptRecipeIngredient(recipe.getOutput().get(iRecipe), all.get(iAll).getOutput().get(iRecipe))) {haveDifference = true; break;}
            }
            if (!haveDifference) return iAll;
        }
        return -1;
    }

    private boolean isGivenIngredientInterceptRecipeIngredient(Ingredients g, Ingredients r) {
        if (g == Ingredients.B && r != Ingredients.B) return false;
        else if (g == Ingredients.W && r != Ingredients.W) return false;
        else if (g == Ingredients.R && (r != Ingredients.R && r != Ingredients.RU && r != Ingredients.RG)) return false;
        else if (g == Ingredients.U && (r != Ingredients.U && r != Ingredients.RU && r != Ingredients.UG)) return false;
        else if (g == Ingredients.G && (r != Ingredients.G && r != Ingredients.RG && r != Ingredients.UG)) return false;
        else return true;
    }
}

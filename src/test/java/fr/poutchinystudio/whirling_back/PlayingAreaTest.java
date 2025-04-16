package fr.poutchinystudio.whirling_back;

import fr.poutchinystudio.whirling_back.enums.Arcanas;
import fr.poutchinystudio.whirling_back.enums.Directions;
import fr.poutchinystudio.whirling_back.enums.Ingredients;
import fr.poutchinystudio.whirling_back.util.PlayingArea;
import fr.poutchinystudio.whirling_back.util.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayingAreaTest {

    @Test
    void checkRealInputOutput() {
        PlayingArea playingArea = new PlayingArea();
        playingArea.setWorkbench(List.of(Ingredients.B, Ingredients.R, Ingredients.R, Ingredients.G));
        Recipe r0 = new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.W, Ingredients.RU), Directions.ONE, List.of(Ingredients.U));
        Recipe r0given = new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.W, Ingredients.R), Directions.ONE, List.of(Ingredients.U));
        Recipe r1 = new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.R, Ingredients.G), Directions.ONE, List.of(Ingredients.W));
        Recipe r2 = new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.B, Ingredients.RU), Directions.ONE, List.of(Ingredients.W));
        playingArea.setSkills(List.of(r0, r1, r2));
        playingArea.setSkillsPrepared(List.of(r1, r0given));

        Assertions.assertNotNull(playingArea.realInputOutput());
    }
}

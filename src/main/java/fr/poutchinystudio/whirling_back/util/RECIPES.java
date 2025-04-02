package fr.poutchinystudio.whirling_back.util;

import fr.poutchinystudio.whirling_back.enums.Arcanas;
import fr.poutchinystudio.whirling_back.enums.Directions;
import fr.poutchinystudio.whirling_back.enums.Ingredients;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class RECIPES {
    private final List<Recipe> all = new ArrayList<>(Arrays.asList(
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.GU), Directions.BOTH, List.of(Ingredients.R, Ingredients.R)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.RU), Directions.ONE, List.of(Ingredients.RG, Ingredients.R)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.W, Ingredients.G), Directions.BOTH, List.of(Ingredients.B)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.W, Ingredients.U, Ingredients.R, Ingredients.G), Directions.BOTH, List.of(Ingredients.B, Ingredients.G, Ingredients.G)),
            new Recipe(List.of(Arcanas.BOOK, Arcanas.RAVEN), List.of(Ingredients.G, Ingredients.U), Directions.BOTH, List.of(Ingredients.W)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.CAULDRON), List.of(Ingredients.W, Ingredients.U), Directions.ONE, List.of(Ingredients.W, Ingredients.RG)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.G, Ingredients.G, Ingredients.G), Directions.ONE, List.of(Ingredients.W, Ingredients.R, Ingredients.U)),
            new Recipe(List.of(Arcanas.BOOK, Arcanas.RAVEN), List.of(Ingredients.U), Directions.ONE, List.of(Ingredients.W, Ingredients.G)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.B), Directions.ONE, List.of(Ingredients.B, Ingredients.R, Ingredients.U)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.W), Directions.ONE, List.of(Ingredients.W, Ingredients.U, Ingredients.U)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.RAVEN), List.of(), Directions.ONE, List.of(Ingredients.U, Ingredients.R, Ingredients.G)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.U, Ingredients.G, Ingredients.R), Directions.BOTH, List.of(Ingredients.R, Ingredients.R, Ingredients.G)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.B), Directions.ONE, List.of(Ingredients.B, Ingredients.G, Ingredients.U)),
            new Recipe(List.of(), List.of(Ingredients.B, Ingredients.B), Directions.ONE, List.of(Ingredients.W, Ingredients.U, Ingredients.U, Ingredients.G, Ingredients.G)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.CAULDRON), List.of(Ingredients.U, Ingredients.U), Directions.BOTH, List.of(Ingredients.G, Ingredients.R)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.CAULDRON, Arcanas.BOOK), List.of(Ingredients.U), Directions.ONE, List.of(Ingredients.W)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.RG), Directions.ONE, List.of(Ingredients.GU, Ingredients.G)),
            new Recipe(List.of(Arcanas.BOOK, Arcanas.BOOK), List.of(Ingredients.R, Ingredients.R, Ingredients.GU, Ingredients.GU), Directions.ONE, List.of(Ingredients.B)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.U, Ingredients.U, Ingredients.U), Directions.ONE, List.of(Ingredients.W, Ingredients.G, Ingredients.R)),
            new Recipe(List.of(), List.of(Ingredients.R, Ingredients.R), Directions.BOTH, List.of(Ingredients.G, Ingredients.G, Ingredients.U, Ingredients.R)),
            new Recipe(List.of(Arcanas.CAULDRON, Arcanas.CAULDRON), List.of(Ingredients.W), Directions.BOTH, List.of(Ingredients.G, Ingredients.R, Ingredients.U)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.CAULDRON, Arcanas.BOOK), List.of(Ingredients.G), Directions.ONE, List.of(Ingredients.W)),
            new Recipe(List.of(Arcanas.BOOK, Arcanas.RAVEN), List.of(Ingredients.G, Ingredients.G), Directions.BOTH, List.of(Ingredients.U, Ingredients.R)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.W, Ingredients.U), Directions.BOTH, List.of(Ingredients.B)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.B), Directions.ONE, List.of(Ingredients.B, Ingredients.G, Ingredients.R)),
            new Recipe(List.of(Arcanas.CAULDRON, Arcanas.BOOK), List.of(Ingredients.G, Ingredients.R), Directions.BOTH, List.of(Ingredients.W)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.CAULDRON), List.of(Ingredients.R, Ingredients.U), Directions.BOTH, List.of(Ingredients.W)),
            new Recipe(List.of(), List.of(Ingredients.B, Ingredients.W, Ingredients.U, Ingredients.R, Ingredients.G), Directions.ONE, List.of(Ingredients.B, Ingredients.B)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.CAULDRON), List.of(Ingredients.U), Directions.ONE, List.of(Ingredients.U, Ingredients.G, Ingredients.R)),
            new Recipe(List.of(), List.of(Ingredients.W, Ingredients.W), Directions.ONE, List.of(Ingredients.R, Ingredients.R, Ingredients.R)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.CAULDRON), List.of(Ingredients.G), Directions.ONE, List.of(Ingredients.W, Ingredients.R)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.RG), Directions.BOTH, List.of(Ingredients.U, Ingredients.U)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.W, Ingredients.U, Ingredients.U), Directions.ONE, List.of(Ingredients.B, Ingredients.G, Ingredients.R)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.GU), Directions.ONE, List.of(Ingredients.RU, Ingredients.U)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.RAVEN, Arcanas.BOOK, Arcanas.BOOK, Arcanas.CAULDRON, Arcanas.CAULDRON), List.of(), Directions.ONE, List.of()),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.RU), Directions.BOTH, List.of(Ingredients.G, Ingredients.G)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.U, Ingredients.U), Directions.ONE, List.of(Ingredients.W, Ingredients.G, Ingredients.G)),
            new Recipe(List.of(Arcanas.BOOK, Arcanas.RAVEN), List.of(Ingredients.R), Directions.ONE, List.of(Ingredients.U, Ingredients.G, Ingredients.R)),
            new Recipe(List.of(), List.of(Ingredients.U, Ingredients.U), Directions.BOTH, List.of(Ingredients.R, Ingredients.R, Ingredients.G, Ingredients.U)),
            new Recipe(List.of(), List.of(Ingredients.W, Ingredients.W), Directions.ONE, List.of(Ingredients.U, Ingredients.U, Ingredients.U)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.CAULDRON, Arcanas.BOOK), List.of(Ingredients.R), Directions.ONE, List.of(Ingredients.U)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.W, Ingredients.R, Ingredients.R), Directions.ONE, List.of(Ingredients.B, Ingredients.G, Ingredients.U)),
            new Recipe(List.of(), List.of(Ingredients.G, Ingredients.G), Directions.BOTH, List.of(Ingredients.U, Ingredients.U, Ingredients.R, Ingredients.G)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.W, Ingredients.U, Ingredients.R, Ingredients.G), Directions.BOTH, List.of(Ingredients.R, Ingredients.R, Ingredients.B)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.W, Ingredients.U, Ingredients.R, Ingredients.G), Directions.BOTH, List.of(Ingredients.U, Ingredients.U, Ingredients.B)),
            new Recipe(List.of(Arcanas.CAULDRON, Arcanas.BOOK), List.of(Ingredients.R), Directions.ONE, List.of(Ingredients.W, Ingredients.U)),
            new Recipe(List.of(Arcanas.CAULDRON, Arcanas.BOOK), List.of(Ingredients.G), Directions.ONE, List.of(Ingredients.U, Ingredients.G, Ingredients.R)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.U, Ingredients.G, Ingredients.R), Directions.BOTH, List.of(Ingredients.U, Ingredients.G, Ingredients.G)),
            new Recipe(List.of(Arcanas.BOOK, Arcanas.CAULDRON), List.of(Ingredients.W, Ingredients.R), Directions.ONE, List.of(Ingredients.W, Ingredients.GU)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.U, Ingredients.G, Ingredients.R), Directions.BOTH, List.of(Ingredients.R, Ingredients.U, Ingredients.U)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.W, Ingredients.G, Ingredients.G), Directions.ONE, List.of(Ingredients.B, Ingredients.R, Ingredients.U)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.G, Ingredients.G), Directions.ONE, List.of(Ingredients.W, Ingredients.R, Ingredients.R)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.W, Ingredients.R), Directions.BOTH, List.of(Ingredients.B)),
            new Recipe(List.of(Arcanas.CAULDRON), List.of(Ingredients.W), Directions.ONE, List.of(Ingredients.W, Ingredients.G, Ingredients.G)),
            new Recipe(List.of(Arcanas.CAULDRON, Arcanas.BOOK), List.of(Ingredients.R, Ingredients.R), Directions.BOTH, List.of(Ingredients.G, Ingredients.U)),
            new Recipe(List.of(Arcanas.RAVEN), List.of(Ingredients.R, Ingredients.R, Ingredients.R), Directions.ONE, List.of(Ingredients.W, Ingredients.U, Ingredients.G)),
            new Recipe(List.of(), List.of(Ingredients.W, Ingredients.W), Directions.ONE, List.of(Ingredients.G, Ingredients.G, Ingredients.G)),
            new Recipe(List.of(Arcanas.RAVEN, Arcanas.BOOK), List.of(Ingredients.W, Ingredients.G), Directions.ONE, List.of(Ingredients.W, Ingredients.RU)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.W), Directions.ONE, List.of(Ingredients.W, Ingredients.R, Ingredients.R)),
            new Recipe(List.of(Arcanas.BOOK), List.of(Ingredients.R, Ingredients.R), Directions.ONE, List.of(Ingredients.W, Ingredients.U, Ingredients.U))
            ));
}

package fr.poutchinystudio.whirling_back.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class WITCHES {
    public static final List<Witch> all = new ArrayList<>(Arrays.asList(
            new Witch("Initiée", List.of(0, 1, 4, 4, 4), "Avant de choisir une recette, défausse cette carte et pioche 4 nouvelles recettes."),
            new Witch("Clairvoyante", List.of(1, 2, 4, 4, 4), "Tu as une taille de main de 5 cartes."),
            new Witch("Casse-cou", List.of(0, 1, 4, 4, 4), "Au moment du choix de la recette, laisser le hasard choisir. Si tu fais ainsi, tu peux déclencher un effet d’arcane."),
            new Witch("Pouvoir secret", List.of(0, 0, 4, 4, 4), "[B][W][U][R][G] > ???. Quand la recette du pouvoir secret est utilisé, défausse la."),
            new Witch("Studieuse", List.of(1, 2, 3, 3, 3), "Une fois par tour vous pouvez dépenser 2 [W] pour déclencher l’effet [BOOK]."),
            new Witch("Lunatique", List.of(1, 1, 5, 3, 5), "[R][R] <> [G][G]. Après avoir passé les cartes de recettes, tourne la première recette."),
            new Witch("Extralucide", List.of(1, 0, 5, 5, 0), "0 > [W][G]"),
            new Witch("Chuchoteuse", List.of(0, 1, 2, 1, 9), "[G][G][G] > ?"),
            new Witch("Marchande", List.of(1, 1, 5, 5, 5), "Après avoir produit les ingrédients, si il y au moins 1 ingrédient de chaque type, prend en un pour le mettre dans ton Cercle."),
            new Witch("Invocatrice", List.of(1, 0, 2, 5, 2), "[W][W][W] > [U][U][U][U]"),
            new Witch("Tête-en-l’air", List.of(0, 0, 5, 5, 5), "Tu peux tourner toutes les cartes quand tu les joues."),
            new Witch("Brasseuse", List.of(0, 2, 4, 4, 4), "Pour chaque symbole [CAULDRON] de la recette choisie, prend un de tes ingrédients à faire passer."),
            new Witch("Incontrôlable", List.of(0, 0, 2, 2, 2), "[B] > [W][R]. Avant de jouer une recette, choisi 3 ingrédients d’un type ajouter à ton workbench.")
    ));
}

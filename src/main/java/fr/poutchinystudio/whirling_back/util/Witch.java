package fr.poutchinystudio.whirling_back.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.poutchinystudio.whirling_back.enums.Ingredients;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Witch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public Long id;
    private String name;
    private List<Integer> ingredients;
    private String power;

    public Witch(String name, List<Integer> ingredients, String power) {
        this.name = name;
        this.ingredients = ingredients;
        this.power = power;
    }

    public List<Ingredients> getIngredientsLetters() {
        List<Ingredients> ingredientsLetters = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++){
            for (int nb = 0; nb < ingredients.get(i); nb++) {
                ingredientsLetters.add(List.of(Ingredients.B, Ingredients.W, Ingredients.R, Ingredients.U, Ingredients.G).get(i));
            }
        }
        return ingredientsLetters;
    }
}

package fr.poutchinystudio.whirling_back.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.poutchinystudio.whirling_back.enums.Arcanas;
import fr.poutchinystudio.whirling_back.enums.Directions;
import fr.poutchinystudio.whirling_back.enums.Ingredients;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public Long id;
    private List<Arcanas> arcana;
    private List<Ingredients> input;
    private Directions direction;
    private List<Ingredients> output;

    public Recipe(List<Arcanas> arcana, List<Ingredients> input, Directions direction, List<Ingredients> output) {
        this.arcana = arcana;
        this.input = input;
        this.direction = direction;
        this.output = output;
    }

    public void fixDirection() {
        direction = Directions.ONE;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Arcanas a : arcana) {
            str.append(a).append(" ");
        }
        str.append("/ ");
        for (Ingredients i : input) {
            str.append(i).append(" ");
        }
        str.append(">>> ");
        for (Ingredients o : output) {
            str.append(o).append(" ");
        }
        return str.toString();
    }
}

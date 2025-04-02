package fr.poutchinystudio.whirling_back.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
}

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
}

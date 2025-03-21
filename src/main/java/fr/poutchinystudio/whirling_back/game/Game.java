package fr.poutchinystudio.whirling_back.game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    String id;
    String password;
    String ownerId;
    long date;
    boolean isStarted;
    ArrayList<String> playersId;
}

package fr.poutchinystudio.whirling_back.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private String id;
    private String password;
    private String ownerName;
    private long date;
    private List<String> playersName;
}

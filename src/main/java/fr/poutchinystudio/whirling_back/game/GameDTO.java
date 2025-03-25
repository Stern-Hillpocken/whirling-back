package fr.poutchinystudio.whirling_back.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    public String id;
    public String password;
    public String ownerName;
    public long date;
    public ArrayList<String> playersName;
}

package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.enums.Phases;
import fr.poutchinystudio.whirling_back.util.PlayingArea;
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
    private Boolean isStarted;
    private List<String> playersName;
    private Phases currentPhase;
    private List<Boolean> areReady;
    private List<PlayingArea> playingAreas;
}

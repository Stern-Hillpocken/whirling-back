package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService service;

    @GetMapping("/all-games")
    public List<GameInfo> allGames () {
        return this.service.allNotStartedGames();
    }

    @PostMapping("/creation")
    public GameLogin creation(
            @RequestBody OneValueObject ovo
            ) {
        return this.service.creation(ovo.value);
    }
}

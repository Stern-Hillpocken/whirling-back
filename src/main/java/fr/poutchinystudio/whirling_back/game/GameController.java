package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService service;

    @GetMapping("/all-games")
    public List<GameInfo> allGames() {
        return this.service.allNotStartedGames();
    }

    @PostMapping("/creation")
    public GameLogin creation(
            @RequestBody OneValueObject ovo
    ) {
        return this.service.creation(ovo.value);
    }

    @GetMapping("/game/{gameId}")
    public GameDTO myGame(
            @PathVariable String gameId,
            @RequestParam("psw") String gamePassword
    ) {
        return this.service.myGame(gameId, gamePassword);
    }

    @PostMapping("/move-player/clockwise")
    public void movePlayerClockwise (
            @RequestBody OneValueObject ovo
    ) {
        this.service.movePlayer(ovo.value, "clockwise");
    }

    @PostMapping("/move-player/anticlockwise")
    public void movePlayerAntiClockwise (
            @RequestBody OneValueObject ovo
    ) {
        this.service.movePlayer(ovo.value, "anticlockwise");
    }
}

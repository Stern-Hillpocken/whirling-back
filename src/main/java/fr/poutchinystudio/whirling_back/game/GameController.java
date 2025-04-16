package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import fr.poutchinystudio.whirling_back.util.Recipe;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{gameId}")
    public GameDTO myGame(
            @PathVariable String gameId,
            @RequestParam("psw") String gamePassword
    ) {
        return this.service.getGame(gameId, gamePassword);
    }

    @PostMapping("/move-player/clockwise")
    public void movePlayerClockwise(
            @RequestBody OneValueObject ovo
    ) {
        this.service.movePlayer(ovo.value, "clockwise");
    }

    @PostMapping("/move-player/anticlockwise")
    public void movePlayerAntiClockwise(
            @RequestBody OneValueObject ovo
    ) {
        this.service.movePlayer(ovo.value, "anticlockwise");
    }

    @PostMapping("/launch")
    public void launch(
            @RequestBody OneValueObject ovo
    ) {
        this.service.launch(ovo.value);
    }

    @GetMapping("/my-index")
    public OneValueObject myIndex() {
        return this.service.myIndex();
    }

    @PostMapping("/ready/setup")
    public void readySetup(
            @RequestBody OneValueObject ovo
    ) {
        this.service.readySetup(ovo.value);
    }

    @PostMapping("/ready/recipe")
    public void readyRecipe(
            @RequestBody Recipe recipe
    ) {
        this.service.readyRecipe(recipe);
    }

    @PostMapping("/ready/produce")
    public void readyProduce(
            @RequestBody List<Recipe> triggeredSkills
    ) {
        this.service.readyProduce(triggeredSkills);
    }
}

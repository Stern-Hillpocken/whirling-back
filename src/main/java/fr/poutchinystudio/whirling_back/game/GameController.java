package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.identification.Identification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService service;

    @PostMapping("/creation")
    public GameLogin creation(
            @RequestBody GameCreation gameCreation
            ) {
        return this.service.creation(gameCreation.getPassword());
    }
}

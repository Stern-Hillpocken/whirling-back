package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private GameRepository repository;

    GameLogin creation(String password) {
        password = password.substring(0, 9);
        String gameId;
        do {
            gameId = Utils.letterShuffler(9);
        } while (repository.findById(gameId).isPresent());

        Game newGame = new Game(gameId, password);
        repository.save(newGame);
        return new GameLogin(gameId, password);
    }
}

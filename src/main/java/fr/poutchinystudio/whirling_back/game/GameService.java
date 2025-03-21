package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.user.UserRepository;
import fr.poutchinystudio.whirling_back.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    GameLogin creation(String password) {
        if (password.length() > 9) password = password.substring(0, 9);
        if (password.length() == 0) password = "pass";

        String gameId;
        do {
            gameId = Utils.letterShuffler(9);
        } while (repository.findById(gameId).isPresent());

        Game newGame = new Game(
                gameId,
                password,
                SecurityContextHolder.getContext().getAuthentication().getName(),
                System.currentTimeMillis(),
                false,
                new ArrayList<>(Collections.singleton(SecurityContextHolder.getContext().getAuthentication().getName()))
        );
        repository.save(newGame);

        messagingTemplate.convertAndSend(
                "/general/game-creation",
                new GameInfo(
                        newGame.getDate(),
                        userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName()).get().getName(),
                        newGame.getId()
                )
        );

        return new GameLogin(gameId, password);
    }

    public List<GameInfo> allNotStartedGames() {
        List<Game> all = repository.findAll();
        List<GameInfo> games = new ArrayList<>();
        all.forEach(game -> {
            if (!game.isStarted) {
                games.add(new GameInfo(
                        game.getDate(),
                        userRepository.findById(game.getOwnerId()).get().getName(),
                        game.getId()
                ));
            }
        });
        return games;
    }

}

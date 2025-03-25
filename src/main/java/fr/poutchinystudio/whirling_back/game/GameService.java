package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.user.UserService;
import fr.poutchinystudio.whirling_back.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository repository;
    private final UserService userService;
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
                new ArrayList<>(Collections.singleton(Utils.jwtUserId()))
        );
        repository.save(newGame);

        messagingTemplate.convertAndSend(
                "/general/game-creation",
                new GameInfo(
                        newGame.getDate(),
                        userService.findById(Utils.jwtUserId()).getName(),
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
                        userService.findById(game.getOwnerId()).getName(),
                        game.getId()
                ));
            }
        });
        return games;
    }

    public GameDTO myGame(String gameId, String gamePassword) {
        Optional<Game> optionalGame = repository.findById(gameId);
        if (optionalGame.isEmpty()) return null;
        Game game = optionalGame.get();
        if (!game.getPassword().equals(gamePassword)) return null;

        if (!game.isStarted() && !game.getPlayersId().contains(Utils.jwtUserId())) {
            game.addPlayerId(Utils.jwtUserId());
            repository.save(game);
            messagingTemplate.convertAndSend(
                "/game/" + game.getId() + "?psw=" + game.getPassword(),
                convertToDTO(game)
            );
        }

        return convertToDTO(game);
    }

    private GameDTO convertToDTO(Game game) {
        return new GameDTO(
                game.getId(),
                game.getPassword(),
                userService.idToName(game.getOwnerId()),
                game.getDate(),
                playersName(game.getPlayersId())
        );
    }

    private ArrayList<String> playersName(ArrayList<String> playersId) {
        ArrayList<String> pNames = new ArrayList<>();
        for (String pId : playersId) {
            pNames.add(userService.idToName(pId));
        }
        return pNames;
    }

}

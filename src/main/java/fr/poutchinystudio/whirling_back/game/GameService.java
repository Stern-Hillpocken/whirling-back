package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import fr.poutchinystudio.whirling_back.enums.Phases;
import fr.poutchinystudio.whirling_back.user.User;
import fr.poutchinystudio.whirling_back.user.UserService;
import fr.poutchinystudio.whirling_back.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        newGame.addPlayer(Utils.jwtUserId());
        repository.save(newGame);

        User user = userService.findById(Utils.jwtUserId());
        user.setGame(newGame.getId());
        userService.save(user);

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
            if (!game.isStarted()) {
                games.add(new GameInfo(
                        game.getDate(),
                        userService.findById(game.getOwnerId()).getName(),
                        game.getId()
                ));
            }
        });
        return games;
    }

    public GameDTO getGame(String gameId, String gamePassword) {
        Optional<Game> optionalGame = repository.findById(gameId);
        if (optionalGame.isEmpty()) return null;
        Game game = optionalGame.get();
        if (!game.getPassword().equals(gamePassword)) return null;

        if (!game.isStarted() && !game.getPlayersId().contains(Utils.jwtUserId())) {
            game.addPlayer(Utils.jwtUserId());
            repository.save(game);
            User user = userService.findById(Utils.jwtUserId());
            user.setGame(game.getId());
            userService.save(user);
            pushWsNotification(game);
        }

        if (game.getPlayersId().contains(Utils.jwtUserId())) {
            return convertToDTO(game);
        }
        return null;
    }

    private GameDTO convertToDTO(Game game) {
        return new GameDTO(
                game.getId(),
                game.getPassword(),
                userService.idToName(game.getOwnerId()),
                game.getDate(),
                game.isStarted(),
                playersName(game.getPlayersId()),
                game.getCurrentPhase(),
                game.getAreReady(),
                game.getPlayingAreas()
        );
    }

    private List<String> playersName(List<String> playersId) {
        List<String> pNames = new ArrayList<>();
        for (String pId : playersId) {
            pNames.add(userService.idToName(pId));
        }
        return pNames;
    }

    public void movePlayer(String playerIdInArray, String clockWay) {
        int startPosition = Integer.parseInt(playerIdInArray);

        User user = userService.findById(Utils.jwtUserId());
        Optional<Game> oGame = repository.findById(user.getGame());
        if (oGame.isEmpty()) return;

        Game game = oGame.get();
        if (!game.getOwnerId().equals(user.getId())) return;
        if (game.isStarted()) return;

        int endPosition = 0;
        if (startPosition == 0 && clockWay.equals("clockwise")) {
            String name = game.getPlayersId().remove(1);
            game.getPlayersId().add(game.getPlayersId().size()-1, name);
        } else if (startPosition == 0 && clockWay.equals("anticlockwise")) {
            String name = game.getPlayersId().remove(game.getPlayersId().size()-1);
            game.getPlayersId().add(1, name);
        } else if (startPosition == game.getPlayersId().size()-1 && clockWay.equals("clockwise")) {
            String name = game.getPlayersId().remove(game.getPlayersId().size()-1);
            game.getPlayersId().add(1, name);
        } else if (startPosition == 1 && clockWay.equals("anticlockwise")) {
            String name = game.getPlayersId().remove(1);
            game.getPlayersId().add(game.getPlayersId().size(), name);
        } else if (clockWay.equals("anticlockwise")) {
            String name = game.getPlayersId().remove(startPosition);
            game.getPlayersId().add(startPosition-1, name);
        } else {
            String name = game.getPlayersId().remove(startPosition);
            game.getPlayersId().add(startPosition+1, name);
        }
        repository.save(game);
        pushWsNotification(game);
    }

    private void pushWsNotification(Game game) {
        messagingTemplate.convertAndSend(
                "/game/" + game.getId() + "?psw=" + game.getPassword(),
                convertToDTO(game)
        );
    }

    public void launch(String strRandomize) {
        User user = userService.findById(Utils.jwtUserId());
        Optional<Game> oGame = repository.findById(user.getGame());
        if (oGame.isEmpty()) return;

        Game game = oGame.get();
        if (!game.getOwnerId().equals(user.getId())) return;
        if (game.isStarted()) return;

        if (strRandomize.equals("randomize")) {
            List<String> playerId = game.getPlayersId();
            game.setPlayersId(new ArrayList<>());
            game.setAreReady(new ArrayList<>());
            game.setPlayingAreas(new ArrayList<>());
            while (!playerId.isEmpty()) {
                int random = Utils.random(0, playerId.size()-1);
                game.addPlayer(playerId.get(random));
                playerId.remove(random);
            }
        }

        game.setStarted(true);
        game.setCurrentPhase(Phases.SETUP);

        for (int i = 0; i < game.getPlayersId().size(); i++) {
            game.getPlayingAreas().get(i).setupWitches();
        }

        repository.save(game);
        pushWsNotification(game);
    }

    public void readySetup(String characterIndex) {
        User user = userService.findById(Utils.jwtUserId());
        Optional<Game> oGame = repository.findById(user.getGame());
        if (oGame.isEmpty()) return;
        Game game = oGame.get();
        if (!game.getCurrentPhase().equals(Phases.SETUP)) return;

        // Ready
        int userIndex = game.getPlayersId().indexOf(user.getId());
        if (game.getAreReady().get(userIndex)) return;
        game.setReadyFor(userIndex);

        // Set witch
        int witchIndex = Integer.parseInt(characterIndex);
        if (witchIndex != 0 && witchIndex != 1 && witchIndex != 2) witchIndex = 0;
        game.getPlayingAreas().get(userIndex).setupWitch(witchIndex);

        // Next phase
        if (!game.getAreReady().contains(false)) {
            game.cleanAreReady();
            game.goToRecipePhase();
        }

        repository.save(game);
        pushWsNotification(game);
    }

    public void readyRecipe(String recipeIndex) {
        User user = userService.findById(Utils.jwtUserId());
        Optional<Game> oGame = repository.findById(user.getGame());
        if (oGame.isEmpty()) return;
        Game game = oGame.get();
        if (!game.getCurrentPhase().equals(Phases.PLAY_RECIPES)) return;

        // Ready
        int userIndex = game.getPlayersId().indexOf(user.getId());
        if (game.getAreReady().get(userIndex)) return;
        game.setReadyFor(userIndex);

        // Next phase
        if (!game.getAreReady().contains(false)) {
            game.cleanAreReady();
            game.goToProducePhase();
        }

        repository.save(game);
        pushWsNotification(game);
    }

    public void readyProduce() {
        User user = userService.findById(Utils.jwtUserId());
        Optional<Game> oGame = repository.findById(user.getGame());
        if (oGame.isEmpty()) return;
        Game game = oGame.get();
        if (!game.getCurrentPhase().equals(Phases.PRODUCE_INGREDIENTS)) return;

        // Ready
        int userIndex = game.getPlayersId().indexOf(user.getId());
        if (game.getAreReady().get(userIndex)) return;
        game.setReadyFor(userIndex);

        // Next phase
        if (!game.getAreReady().contains(false)) {
            game.cleanAreReady();
            game.goToRecipePhase();
        }

        repository.save(game);
        pushWsNotification(game);
    }

    public OneValueObject myIndex() {
        User user = userService.findById(Utils.jwtUserId());
        Optional<Game> oGame = repository.findById(user.getGame());
        if (oGame.isEmpty()) return null;
        Game game = oGame.get();

        int userIndex = game.getPlayersId().indexOf(user.getId());
        return new OneValueObject(Integer.toString(userIndex));
    }

}

package com.example.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class GameServiceIntegrationTest {

    private GameService gameService;

    @BeforeEach
    public void setUp() {
        gameService = new GameService();
    }

    @Test
    public void testCreateGame() {
        // Arrange
        String gameId = "game1";
        List<Player> players = new ArrayList<>();
        players.add(new Player(1,"John"));
        players.add(new Player(2,"Jane"));

        // Act
        gameService.createGame(gameId, players);
        Game game = gameService.getGame(gameId);

        // Assert
        Assertions.assertNotNull(game);
        Assertions.assertEquals(gameId, game.getGameId());
        Assertions.assertEquals(players.size(), game.getPlayers().size());
    }

    @Test
    public void testStartGame() {
        // Arrange
        String gameId = "game1";
        List<Player> players = new ArrayList<>();
        players.add(new Player(1,"John"));
        players.add(new Player(2,"Jane"));
        gameService.createGame(gameId, players);

        // Act
        gameService.startGame(gameId);
        Game game = gameService.getGame(gameId);

        // Assert
        Assertions.assertTrue(game.isStarted());
    }

    @Test
    public void testAddPlayerToGame() {
        // Arrange
        String gameId = "game1";
        List<Player> players = new ArrayList<>();
        players.add(new Player(1,"John"));
        gameService.createGame(gameId, players);
        Player player = new Player(2,"Jane");

        // Act
        gameService.addPlayerToGame(gameId, player);
        Game game = gameService.getGame(gameId);

        // Assert
        Assertions.assertEquals(players.size() + 1, game.getPlayers().size());
        Assertions.assertTrue(game.getPlayers().contains(player));
    }

    @Test
    public void testPlayTurn() {
        // Arrange
        String gameId = "game1";
        List<Player> players = new ArrayList<>();
        players.add(new Player(1,"John"));
        players.add(new Player(2,"Jane"));
        gameService.createGame(gameId, players);
        Player player = gameService.getGame(gameId).getPlayers().get(0);
        AppariementService appariementService = new AppariementService(new ProbabilityService());

        // Act
        Map<String, Object> result = gameService.playTurn(gameId, player, appariementService);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.containsKey("dice"));
        Assertions.assertTrue(result.containsKey("chosenCategory"));
    }
}


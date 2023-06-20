package com.example.game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class GameServiceTest {

    private GameService gameService;

    @Mock
    private AppariementService appariementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameService();
    }

    @Test
    public void testGenerateUniqueId() {
        String uniqueId = gameService.generateUniqueId();

        Assertions.assertNotNull(uniqueId);
        Assertions.assertTrue(uniqueId.startsWith("GAME_"));
    }

    @Test
    public void testCreateGame() {
        String gameId = "game1";
        List<Player> players = new ArrayList<>();
        Player player1 = new Player(1, "John");
        Player player2 = new Player(2, "Jane");
        players.add(player1);
        players.add(player2);

        gameService.createGame(gameId, players);

        Game game = gameService.getGame(gameId);
        Assertions.assertNotNull(game);
        Assertions.assertEquals(gameId, game.getGameId());
        Assertions.assertEquals(players, game.getPlayers());
    }

    @Test
    public void testStartGame_GameExists() {
        String gameId = "game1";
        Game game = new Game(gameId);
        gameService.addGame(gameId, game);

        gameService.startGame(gameId);

        Assertions.assertTrue(game.isStarted());
    }

    @Test
    public void testStartGame_GameDoesNotExist() {
        String gameId = "game1";

        gameService.startGame(gameId);


    }

    @Test
    public void testAddPlayerToGame_GameExists() {
        String gameId = "game1";
        Game game = new Game(gameId);
        gameService.addGame(gameId, game);
        Player player = new Player(1, "John");

        gameService.addPlayerToGame(gameId, player);

        Assertions.assertTrue(game.getPlayers().contains(player));
    }

    @Test
    public void testAddPlayerToGame_GameDoesNotExist() {
        String gameId = "game1";
        Player player = new Player(1, "John");

        gameService.addPlayerToGame(gameId, player);


    }

    @Test
    public void testPlayTurn() {
        String gameId = "game1";
        Game game = new Game(gameId);
        gameService.addGame(gameId, game);
        Player player = new Player(1, "John");
        int[] dice = {1, 2, 3, 4, 5};
        int chosenCategory = 7;

        when(appariementService.appariementJoueurProbabilite(player, chosenCategory, dice)).thenReturn(null);

        Map<String, Object> result = gameService.playTurn(gameId, player, appariementService);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(chosenCategory, result.get("chosenCategory"));

    }



}


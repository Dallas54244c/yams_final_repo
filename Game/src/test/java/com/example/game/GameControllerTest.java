package com.example.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GameControllerTest {

    @Mock
    private GameService gameService;

    private GameController gameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController(gameService, mock(AppariementService.class));
    }

    @Test
    void createGame_ReturnsGameId() {
        String gameId = "12345";
        when(gameService.generateUniqueId()).thenReturn(gameId);

        String result = gameController.createGame();

        assertEquals("Partie créée avec l'identifiant : " + gameId, result);
        verify(gameService).addGame(eq(gameId), any(Game.class));
    }


    @Test
    void joinGame_ValidGameId_ReturnsSuccessMessage() {
        String gameId = "12345";
        long playerId = 123456789L;
        Player player = new Player(playerId, "John");
        Game game = new Game(gameId);

        when(gameService.getGame(gameId)).thenReturn(game);

        String result = gameController.joinGame(player, gameId);

        assertEquals("John a rejoint la partie " + gameId, result);
        // Vérifier que le joueur a été ajouté à la partie
        assertTrue(game.getPlayers().contains(player));


    }



    @Test
    void joinGame_InvalidGameId_ReturnsErrorMessage() {
        String gameId = "12345";
        long playerId = 123456789L;;
        Player player = new Player(playerId);
        when(gameService.getGame(gameId)).thenReturn(null);

        String result = gameController.joinGame(player, gameId);

        assertEquals("La partie " + gameId + " n'existe pas.", result);
        verify(gameService, never()).addGame(anyString(), any(Game.class));
    }

    @Test
    void startGame_ValidGameId_ReturnsSuccessMessage() {
        String gameId = "12345";
        Game game = new Game(gameId);
        when(gameService.getGame(gameId)).thenReturn(game);

        String result = gameController.startGame(gameId);

        assertEquals("La partie " + gameId + " a démarré.", result);
        assertTrue(game.isStarted());
    }

    @Test
    void startGame_InvalidGameId_ReturnsErrorMessage() {
        String gameId = "12345";
        when(gameService.getGame(gameId)).thenReturn(null);

        String result = gameController.startGame(gameId);

        assertEquals("La partie " + gameId + " n'existe pas.", result);
        verify(gameService, never()).addGame(anyString(), any(Game.class));
    }

    @Test
    void playTurn_ValidGameAndPlayer_ReturnsSuccessMessage() {
        String gameId = "12345";
        long playerId = 123456789L;
        Player player = new Player(playerId);
        Game game = new Game(gameId);
        game.addPlayer(player);
        game.start();
        when(gameService.getGame(gameId)).thenReturn(game);

        String result = gameController.playTurn(gameId);

        assertEquals("Le tour de jeu de " + player.getName() + " dans la partie " + gameId + " a été effectué.", result);
        // Vérifier la logique spécifique du jeu ici
    }

    @Test
    void playTurn_InvalidGameId_ReturnsErrorMessage() {
        String gameId = "12345";
        long playerId = 123456789L;
        when(gameService.getGame(gameId)).thenReturn(null);

        String result = gameController.playTurn(gameId);

        assertEquals("La partie " + gameId + " n'existe pas.", result);
        // Vérifier que le tour de jeu n'a pas été effectué
    }



}



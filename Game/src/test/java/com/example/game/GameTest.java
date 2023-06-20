package com.example.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private com.example.game.Player player1;
    private com.example.game.Player player2;
    private com.example.game.Player player3;

    @BeforeEach
    void setUp() {
        game = new Game("G1");
        player1 = new com.example.game.Player(1, "John");
        player2 = new com.example.game.Player(2, "Alice");
        player3 = new com.example.game.Player(3, "Bob");
    }

    @Test
    void calculateTotalScores() {
        game.addPlayer(player1);
        game.addPlayer(player2);

        player1.setscore(1, 10);
        player1.setscore(2, 20);
        player2.setscore(1, 15);
        player2.setscore(2, 25);

        Map<com.example.game.Player, Integer> playerScores = game.calculateTotalScores();

        assertEquals(30, playerScores.get(player1));
        assertEquals(40, playerScores.get(player2));
    }

    @Test
    void addPlayer_Successful() {
        game.addPlayer(player1);

        assertTrue(game.getPlayers().contains(player1));
    }

    @Test
    void addPlayer_MaxPlayersReached() {
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        assertTrue(game.getPlayers().contains(player3));
    }

    @Test
    void addPlayers() {
        List<com.example.game.Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        game.addPlayers(players);

        assertTrue(game.getPlayers().contains(player1));
        assertTrue(game.getPlayers().contains(player2));
    }

    @Test
    void moveToNextPlayer() {
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        game.moveToNextPlayer();
        assertEquals(player2, game.getCurrentPlayer());

        game.moveToNextPlayer();
        assertEquals(player3, game.getCurrentPlayer());

        game.moveToNextPlayer();
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void isStarted() {
        assertFalse(game.isStarted());

        game.start();

        assertTrue(game.isStarted());
    }

    @Test
    void getCurrentPlayer() {
        game.addPlayer(player1);
        game.addPlayer(player2);

        assertEquals(player1, game.getCurrentPlayer());

        game.moveToNextPlayer();

        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    void nextPlayer() {
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        game.nextPlayer();
        assertEquals(player2, game.getCurrentPlayer());

        game.nextPlayer();
        assertEquals(player3, game.getCurrentPlayer());

        game.nextPlayer();
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void getPlayers() {
        List<com.example.game.Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        game.addPlayers(players);

        assertEquals(players, game.getPlayers());
    }

    @Test
    void getWinner() {
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        player1.setscore(1, 10);
        player1.setscore(2, 20);
        player2.setscore(1, 15);
        player2.setscore(2, 25);
        player3.setscore(1, 5);
        player3.setscore(2, 30);

        assertEquals(player2, game.getWinner());
    }

    @Test
    void playTurn() {
        game.addPlayer(player1);
        game.addPlayer(player2);

        Map<String, Object> result = game.playTurn(player1);

        assertNotNull(result.get("dice"));
        assertNotNull(result.get("chosenCategory"));

        assertEquals(player2, game.getCurrentPlayer());
    }
}


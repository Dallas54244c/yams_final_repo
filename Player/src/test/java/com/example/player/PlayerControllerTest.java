package com.example.player;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class PlayerControllerTest {
    private PlayerService playerService;
    private PlayerController playerController;

    @BeforeEach
    public void setUp() {
        playerService = Mockito.mock(PlayerService.class);
        playerController = new PlayerController(playerService);
    }

    @Test
    public void testGetPlayer_Success() {
        long playerId = 1;
        Player player = new Player(playerId, "John");
        Mockito.when(playerService.getPlayerById(playerId)).thenReturn(player);

        ResponseEntity<Player> response = playerController.getPlayer(playerId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(player, response.getBody());
    }

    @Test
    public void testGetPlayer_NotFound() {
        long playerId = 1;
        Mockito.when(playerService.getPlayerById(playerId)).thenReturn(null);

        ResponseEntity<Player> response = playerController.getPlayer(playerId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testCreatePlayer_Success() {
        Player player = new Player(1, "John");
        Mockito.when(playerService.createPlayer(player)).thenReturn(player);

        ResponseEntity<Player> response = playerController.createPlayer(player);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(player, response.getBody());
    }

    @Test
    public void testGetAllPlayers() {
        List<Player> players = Arrays.asList(
                new Player(1, "John"),
                new Player(2, "Jane"),
                new Player(3, "Mike")
        );
        Mockito.when(playerService.getAllPlayers()).thenReturn(players);

        ResponseEntity<List<Player>> response = playerController.getAllPlayers();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(players, response.getBody());
    }


    @Test
    public void testGetPlayerTotalScore_NotFound() {
        long playerId = 1;
        Mockito.when(playerService.getPlayerById(playerId)).thenReturn(null);

        ResponseEntity<Integer> response = playerController.getPlayerTotalScore((int) playerId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }
}



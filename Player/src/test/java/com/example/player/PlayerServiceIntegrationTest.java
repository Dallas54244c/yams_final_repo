package com.example.player;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PlayerServiceIntegrationTest {

    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        playerService = new PlayerService();
    }

    @Test
    public void testRegisterPlayer() {
        // Arrange
        long playerId = 1;
        String playerName = "John";

        // Act
        playerService.registerPlayer(playerId, playerName);
        Player player = playerService.getPlayerById(playerId);

        // Assert
        Assertions.assertNotNull(player);
        Assertions.assertEquals(playerId, player.getId());
        Assertions.assertEquals(playerName, player.getName());
    }

    @Test
    public void testGetAllPlayers() {
        // Arrange
        long playerId1 = 1;
        String playerName1 = "John";
        long playerId2 = 2;
        String playerName2 = "Jane";
        playerService.registerPlayer(playerId1, playerName1);
        playerService.registerPlayer(playerId2, playerName2);

        // Act
        List<Player> players = playerService.getAllPlayers();

        // Assert
        Assertions.assertEquals(2, players.size());
        Assertions.assertTrue(players.stream().anyMatch(p -> p.getId() == playerId1 && p.getName().equals(playerName1)));
        Assertions.assertTrue(players.stream().anyMatch(p -> p.getId() == playerId2 && p.getName().equals(playerName2)));
    }

    @Test
    public void testAddHostingServer() {
        // Arrange
        HostingServer server = new HostingServer("server1", 1, 5);

        // Act
        playerService.addHostingServer(server);
        List<HostingServer> servers = playerService.getAvailableServers();

        // Assert
        Assertions.assertEquals(1, servers.size());
        Assertions.assertTrue(servers.contains(server));
    }

    @Test
    public void testFindMatchingServer() {
        // Arrange
        HostingServer server1 = new HostingServer("server1", 1, 5);
        server1.setLevel(3);
        HostingServer server2 = new HostingServer("server2", 1, 5);
        server2.setLevel(5);
        HostingServer server3 = new HostingServer("server3", 1, 5);
        server3.setLevel(4);
        playerService.addHostingServer(server1);
        playerService.addHostingServer(server2);
        playerService.addHostingServer(server3);
        Player player1 = new Player(1, "John");
        player1.setLevel(2);
        Player player2 = new Player(2, "Jane");
        player2.setLevel(4);
        Player player3 = new Player(3, "Bob");
        player3.setLevel(6);

        // Act
        HostingServer result1 = playerService.findMatchingServer(player1);
        HostingServer result2 = playerService.findMatchingServer(player2);
        HostingServer result3 = playerService.findMatchingServer(player3);

        // Assert
        Assertions.assertNotNull(result1);
        Assertions.assertEquals(server2, result2);
        Assertions.assertNull(result3);
    }

    @Test
    public void testCreatePlayer() {
        // Arrange
        Player player = new Player(1,"John");

        // Act
        Player result = playerService.createPlayer(player);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(0, result.getId());
        Assertions.assertEquals(0, result.getScore());
        Assertions.assertEquals(0, result.getTotalDiceRolls());
        Assertions.assertEquals(3, result.getRemainingRolls());
        Assertions.assertFalse(result.isGameStarted());
    }
}



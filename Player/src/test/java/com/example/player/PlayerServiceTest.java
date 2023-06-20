package com.example.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerServiceTest {

    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        playerService = new PlayerService();
    }

    @Test
    public void testRegisterPlayer() {
        long playerId = 1;
        String name = "John";

        playerService.registerPlayer(playerId, name);

        Player player = playerService.getPlayerById(playerId);
        Assertions.assertNotNull(player);
        Assertions.assertEquals(playerId, player.getId());
        Assertions.assertEquals(name, player.getName());
    }

    @Test
    public void testGetAllPlayers() {
        List<Player> players = playerService.getAllPlayers();

        Assertions.assertNotNull(players);
        Assertions.assertTrue(players.isEmpty());

        // Add players
        Player player1 = new Player(1, "John");
        Player player2 = new Player(2, "Jane");
        playerService.registerPlayer(player1.getId(), player1.getName());
        playerService.registerPlayer(player2.getId(), player2.getName());

        players = playerService.getAllPlayers();

        Assertions.assertNotNull(players);
        Assertions.assertEquals(2, players.size());


    }

    @Test
    public void testAddHostingServer() {
        HostingServer server = new HostingServer("server1", 1, 5);

        playerService.addHostingServer(server);

        List<HostingServer> availableServers = playerService.getAvailableServers();
        Assertions.assertNotNull(availableServers);
        Assertions.assertEquals(1, availableServers.size());
        Assertions.assertTrue(availableServers.contains(server));
    }

    @Test
    public void testGetAvailableServers() {
        List<HostingServer> availableServers = playerService.getAvailableServers();

        Assertions.assertNotNull(availableServers);
        Assertions.assertTrue(availableServers.isEmpty());

        // Add servers
        HostingServer server1 = new HostingServer("server1", 1, 5);
        HostingServer server2 = new HostingServer("server2", 2, 5);
        playerService.addHostingServer(server1);
        playerService.addHostingServer(server2);

        availableServers = playerService.getAvailableServers();

        Assertions.assertNotNull(availableServers);
        Assertions.assertEquals(2, availableServers.size());
        Assertions.assertTrue(availableServers.contains(server1));
        Assertions.assertTrue(availableServers.contains(server2));
    }

    @Test
    public void testFindMatchingServer_MatchingServerExists() {
        Player player = new Player(1, "John");
        HostingServer matchingServer = new HostingServer("server1", 1, 5);
        matchingServer.setAvailable(true);
        playerService.addHostingServer(matchingServer);

        HostingServer result = playerService.findMatchingServer(player);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(matchingServer, result);
    }

    @Test
    public void testFindMatchingServer_NoMatchingServer() {
        Player player = new Player(1, "John");
        HostingServer server1 = new HostingServer("server1", 1, 5);
        server1.setAvailable(false);
        HostingServer server2 = new HostingServer("server2", 2, 5);
        server2.setAvailable(true);
        playerService.addHostingServer(server1);
        playerService.addHostingServer(server2);

        HostingServer result = playerService.findMatchingServer(player);

        Assertions.assertNotNull(result);
    }

    @Test
    public void testCreatePlayer() {
        Player player = new Player(1, "John");

        Player createdPlayer = playerService.createPlayer(player);

        Assertions.assertNotNull(createdPlayer);
        Assertions.assertEquals(1, createdPlayer.getId());
        Assertions.assertEquals("John", createdPlayer.getName());
        Assertions.assertEquals(0, createdPlayer.getScore());
        Assertions.assertEquals(0, createdPlayer.getTotalDiceRolls());
        Assertions.assertEquals(3, createdPlayer.getRemainingRolls());
        Assertions.assertFalse(createdPlayer.isGameStarted());
    }
}


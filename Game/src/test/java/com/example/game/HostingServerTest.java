package com.example.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HostingServerTest {
    private HostingServer hostingServer;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        hostingServer = new HostingServer("H1", 1, 5);
        player1 = new Player(1, "John");
        player2 = new Player(2, "Alice");
    }

    @Test
    void addPlayer_Successful() {
        assertTrue(hostingServer.addPlayer(player1));
        assertEquals(1, hostingServer.getPlayers().size());
    }

    @Test
    void addPlayer_MaxPlayersReached() {
        hostingServer.addPlayer(player1);
        hostingServer.addPlayer(player2);

        assertTrue(hostingServer.addPlayer(new Player(3, "Bob")));
        assertEquals(3, hostingServer.getPlayers().size());
    }

    @Test
    void addPlayer_PlayerAlreadyParticipating() {
        hostingServer.addPlayer(player1);

        assertFalse(hostingServer.addPlayer(player1));
        assertEquals(1, hostingServer.getPlayers().size());
    }

    @Test
    void removePlayer_Successful() {
        hostingServer.addPlayer(player1);
        hostingServer.addPlayer(player2);

        assertTrue(hostingServer.removePlayer(player1));
        assertEquals(1, hostingServer.getPlayers().size());
        assertFalse(hostingServer.isPlayerParticipating(player1));
    }

    @Test
    void isPlayerParticipating_True() {
        hostingServer.addPlayer(player1);

        assertTrue(hostingServer.isPlayerParticipating(player1));
    }

    @Test
    void isPlayerParticipating_False() {
        hostingServer.addPlayer(player1);

        assertFalse(hostingServer.isPlayerParticipating(player2));
    }
}


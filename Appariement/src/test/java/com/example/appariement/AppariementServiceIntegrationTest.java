package com.example.appariement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ComponentScan(basePackages = "com.example.appariement")
public class AppariementServiceIntegrationTest {

    @Autowired
    private AppariementService appariementService;

    private List<HostingServer> hostingServers;

    @BeforeEach
    void setUp() {
        hostingServers = new ArrayList<>();
        hostingServers.add(new HostingServer("Server1", 1, 5));
        hostingServers.add(new HostingServer("Server2", 2, 5));
    }

    @Test
    void testFindAvailableHostingServer() {
        // Arrange
        hostingServers.get(0).setAvailable(true);

        // Act
        HostingServer result = appariementService.findAvailableHostingServer(hostingServers);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isAvailable());
    }

    @Test
    void testAppariementJoueurProbabilite() {
        // Arrange
        Player player = new Player(1, "John");
        int coup = 5;
        int[] dice = {1, 2, 3, 4, 5};

        // Act
        String result = appariementService.appariementJoueurProbabilite(player, coup, dice);

        // Assert
        Assertions.assertNull(result);
    }

    @Test
    void testAppariementJoueurHebergeur() {
        // Arrange
         Player player = new  Player(1, "John");
       Player player2 = new  Player(2, "John");
        HostingServer hostingServer = hostingServers.get(0);

        // Act
        appariementService.appariementJoueurHebergeur(player2, hostingServers);

        // Assert
        Assertions.assertFalse(hostingServer.isAvailable());
        Assertions.assertFalse(hostingServer.isPlayerParticipating(player));
    }
}


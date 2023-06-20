package com.example.appariement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AppariementServiceTest {

    private AppariementService appariementService;

    @Mock
    private ProbabilityService probabilityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        appariementService = new AppariementService(probabilityService);
    }

    @Test
    public void testFindAvailableHostingServer() {
        HostingServer availableServer = new HostingServer("server1", 1, 5);
        availableServer.setAvailable(true);

        HostingServer unavailableServer = new HostingServer("server2", 2, 5);
        unavailableServer.setAvailable(false);

        List<HostingServer> hostingServers = new ArrayList<>();
        hostingServers.add(availableServer);
        hostingServers.add(unavailableServer);

        HostingServer result = appariementService.findAvailableHostingServer(hostingServers);

        Assertions.assertEquals(availableServer, result);
    }

    @Test
    public void testFindAvailableHostingServer_NoAvailableServer() {
        HostingServer unavailableServer1 = new HostingServer("server1", 1, 5);
        unavailableServer1.setAvailable(false);

        HostingServer unavailableServer2 = new HostingServer("server2", 2, 5);
        unavailableServer2.setAvailable(false);

        List<HostingServer> hostingServers = new ArrayList<>();
        hostingServers.add(unavailableServer1);
        hostingServers.add(unavailableServer2);

        HostingServer result = appariementService.findAvailableHostingServer(hostingServers);

        Assertions.assertNull(result);
    }

    @Test
    public void testAppariementJoueurProbabilite_HighProbability() {
        Player player = new Player(1, "John");
        int coup = 5;
        int[] dice = {1, 2, 3, 4, 5};

        double probability = 0.7;

        when(probabilityService.calculateProbability(coup, dice)).thenReturn(probability);

        appariementService.appariementJoueurProbabilite(player, coup, dice);


    }

    @Test
    public void testAppariementJoueurProbabilite_LowProbability() {
        Player player = new Player(1, "John");
        int coup = 5;
        int[] dice = {1, 2, 3, 4, 5};

        double probability = 0.3;

        when(probabilityService.calculateProbability(coup, dice)).thenReturn(probability);

        appariementService.appariementJoueurProbabilite(player, coup, dice);


    }

    @Test
    public void testAppariementJoueurHebergeur_AvailableServer() {
         Player player = new  Player(1, "John");
        HostingServer hostingServer = new HostingServer("server1", 1, 5);
        hostingServer.setAvailable(true);

        List<HostingServer> hostingServers = new ArrayList<>();
        hostingServers.add(hostingServer);

        appariementService.appariementJoueurHebergeur(player, hostingServers);


    }

    @Test
    public void testAppariementJoueurHebergeur_NoAvailableServer() {
        Player player = new  Player(1, "John");
        HostingServer unavailableServer = new HostingServer("server1", 1, 5);
        unavailableServer.setAvailable(false);

        List<HostingServer> hostingServers = new ArrayList<>();
        hostingServers.add(unavailableServer);

        appariementService.appariementJoueurHebergeur(player, hostingServers);


    }



}



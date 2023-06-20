package com.example.yams_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class YamsFinalApplication {
    private static GameService gameService;
    private static PlayerService playerService;
    private static ProbabilityService probabilityService;
    private static AppariementService appariementService;
    private static List<HostingServer> hostingServers;

    public static void main(String[] args) {
        // Initialize the services
        gameService = new GameService();
        playerService = new PlayerService();
        probabilityService = new ProbabilityService();
        appariementService = new AppariementService(probabilityService);

        // Initialize the hosting servers
        hostingServers = Arrays.asList(
                new HostingServer("H1", 1,5),
                new HostingServer("H2", 2,5),
                new HostingServer("H3", 3,5)
        );

        // Create the game
        String gameId = "12345";
        List<Player> players = Arrays.asList(
                new Player(1, "John"),
                new Player(2, "Alice"),
                new Player(3, "Bob"),
                new Player(4, "Eve"),
                new Player(5, "Mia")
        );

        gameService.createGame("12345", players);


        // Create the players
        Player player1 = new Player(1, "John");
        Player player2 = new Player(2, "Alice");
        Player player3 = new Player(3, "Bob");
        Player player4 = new Player(4, "Eve");
        Player player5 = new Player(5, "Mia");
        appariementService.appariementJoueurHebergeur(player1,hostingServers);
        appariementService.appariementJoueurHebergeur(player2,hostingServers);
        appariementService.appariementJoueurHebergeur(player3,hostingServers);
        appariementService.appariementJoueurHebergeur(player4,hostingServers);
        appariementService.appariementJoueurHebergeur(player5,hostingServers);

        // Add the players to the game
        gameService.addPlayerToGame(gameId, player1);
        gameService.addPlayerToGame(gameId, player2);
        gameService.addPlayerToGame(gameId, player3);
        gameService.addPlayerToGame(gameId, player4);
        gameService.addPlayerToGame(gameId, player5);
        gameService.startGame(gameId);


        // Play Turns
        for (int i = 0; i < 5; i++) {
            for (Player player : gameService.getGame(gameId).getPlayers()) {
                Map<String, Object> result = gameService.playTurn(gameId, player,appariementService);
                int[] dice = (int[]) result.get("dice");
                int chosenCategory = (int) result.get("chosenCategory");

                // Perform the appariement between player and probability for the current turn
                appariementService.appariementJoueurProbabilite(player, chosenCategory, dice);


                // Calculate and set the score for the player
                int score = player.calculateScore(chosenCategory, dice);
                player.setscore(chosenCategory, score);

                System.out.println("Lancer du joueur " + player.getName() + ": " + Arrays.toString(dice));
                System.out.println("Catégorie choisie par le joueur " + player.getName() + ": " + chosenCategory);
                System.out.println("Score  pour le joueur " + player.getName() + " apres le lancer  " + (i + 1) + ": " + score);
            }
        }

        // Print Final Scores
        for (Player player : gameService.getGame(gameId).getPlayers()) {
            System.out.println("Score final pour le joueur: " + player.getName() + ": " + player.getTotalScore());
        }
        System.out.println("The winner is: " + gameService.getGame(gameId).getWinner().getName());
    }

    @Bean
    public GameController gameController() {
        return new GameController(gameService, appariementService);
    }

    @Bean
    public ProbabilityController probabilityController() {
        return new ProbabilityController(probabilityService);
    }

    @Bean
    public HostingServer hostingServerController(HostingServer hostingServer) {
        return new HostingServer(hostingServer.getServerId(), hostingServer.getLevel(),5);
    }

    @Bean
    public AppariementService appariementService(ProbabilityService probabilityService) {
        return new AppariementService(probabilityService);
    }
}



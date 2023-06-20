package com.example.yams_final;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GameService {
    private final Map<String, Game> games;

    public GameService() {
        this.games = new HashMap<>();
    }

    public String generateUniqueId() {
        // Générer un identifiant unique pour la partie
        // (Vous pouvez utiliser une logique basée sur l'horodatage, UUID, ou autre)
        String gameId = "GAME_" + System.currentTimeMillis();
        return gameId;
    }

    public void createGame(String gameId, List<Player> players) {
        // Créer une nouvelle partie avec l'identifiant et la liste de joueurs fournis
        Game game = new Game(gameId);
        game.addPlayers(players);
        games.put(gameId, game);
    }

    public void startGame(String gameId) {
        // Vérifier si la partie existe
        if (!games.containsKey(gameId)) {
            System.out.println("La partie avec l'identifiant " + gameId + " n'existe pas.");
            return;
        }

        Game game = games.get(gameId);
        // Vérifier si la partie a déjà été démarrée
        if (game.isStarted()) {
            System.out.println("La partie avec l'identifiant " + gameId + " a déjà été démarrée.");
            return;
        }

        // Démarrer la partie
        game.start();
        System.out.println("La partie avec l'identifiant " + gameId + " a été démarrée.");
    }

    public Game getGame(String gameId) {
        // Récupérer la partie correspondant à l'identifiant fourni
        return games.get(gameId);
    }

    public void addPlayerToGame(String gameId, Player player) {
        // Vérifier si la partie existe
        if (!games.containsKey(gameId)) {
            System.out.println("La partie avec l'identifiant " + gameId + " n'existe pas.");
            return ;
        }


        Game game = games.get(gameId);
        // Vérifier si la partie a déjà été démarrée
        if (game.isStarted()) {
            System.out.println("La partie avec l'identifiant " + gameId + " a déjà été démarrée. Impossible d'ajouter un joueur.");
            return;

        }

        // Ajouter le joueur à la partie
        game.addPlayer(player);
        System.out.println("Le joueur " + player.getName() + " a été ajouté à la partie avec l'identifiant " + gameId + ".");

    }
    public void addGame(String gameId, Game game) {
        games.put(gameId, game);
    }
    public void addPlayer(String gameId, Player player) {
        Game game = games.get(gameId);
        if (game != null) {
            game.addPlayer(player);
            System.out.println("Le joueur " + player.getName() + " a rejoint la partie " + gameId + ".");
        } else {
            System.out.println("La partie " + gameId + " n'existe pas.");
        }
    }

    public Map<String, Object> playTurn(String gameId, Player player, AppariementService appariementService) {
        // Roll the dice for the player
        int[] dice = player.rollDice();
        // Choose a category for scoring
        int chosenCategory = player.chooseCategory(dice);

        // Calculate the score based on the chosen category
        int score = player.calculateScore(chosenCategory, dice);
        // Set the score for the player
        player.setscore(chosenCategory,score);

        // Call the appariement method
        appariementService.appariementJoueurProbabilite(player, chosenCategory, dice);

        Map<String, Object> result = new HashMap<>();
        result.put("dice", dice);
        result.put("chosenCategory", chosenCategory);
        return result;
    }


}


package com.example.game;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private GameService gameService;
    Player currentPlayer;
    private String gameId;
    private List<Player> players;
    private boolean started;

    public String getGameId() {
        return gameId;
    }

    private int currentPlayerIndex;

    public Game(String gameId) {
        this.gameId = gameId;
        this.players = new ArrayList<>();
        this.started = false;
        this.currentPlayerIndex = 0;
    }

    public Map<Player, Integer> calculateTotalScores() {
        Map<Player, Integer> playerScores = new HashMap<>();
        for (Player player : players) {
            int totalScore = player.getTotalScore();
            playerScores.put(player, totalScore);
        }
        return playerScores;
    }

    public void addPlayer(Player player) {
        if (this.players.size() < 5)
            this.players.add(player);
    }

    public void addPlayers(List<Player> players) {
        this.players.addAll(players);
    }

    public void moveToNextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0; // Wrap around to the first player if reached the end
        }
        this.currentPlayer = players.get(currentPlayerIndex);
    }

    public boolean isStarted() {
        return started;
    }

    public void start() {
        this.started = true;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }





    // Méthode pour récupérer la liste des joueurs
    public List<Player> getPlayers() {
        return players;
    }





    // Méthode pour récupérer le gagnant de la partie
    public Player getWinner() {
        Player winner = players.get(0);
        for (Player player : players) {
            if (player.getTotalScore() > winner.getTotalScore()) {
                winner = player;
            }
        }
        return winner;
    }


    public Map<String, Object> playTurn(Player player) {
        // Roll the dice for the player
        int[] dice = player.rollDice();
        // Choose a category for scoring
        int chosenCategory = player.chooseCategory(dice);

        // Calculate the score based on the chosen category
        int score = player.calculateScore(chosenCategory, dice);
        // Set the score for the player
        player.setscore(chosenCategory, score);
        moveToNextPlayer();

        // Create a map to hold the dice and chosenCategory values
        Map<String, Object> result = new HashMap<>();
        result.put("dice", dice);
        result.put("chosenCategory", chosenCategory);
        return result;
    }



}



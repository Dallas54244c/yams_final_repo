package com.example.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan(basePackages = "com.example.appariement")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService, AppariementService appariementService) {
        this.gameService = gameService;
    }

    @PostMapping("/games")
    public String createGame() {
        String gameId = gameService.generateUniqueId();
        Game game = new Game(gameId);
        gameService.addGame(gameId, game);
        return "Partie créée avec l'identifiant : " + gameId;
    }

    @PostMapping("/games/{gameId}/players")
    public String joinGame(@RequestBody Player player, @PathVariable String gameId) {
        Game game = gameService.getGame(gameId);
        if (game != null) {
            game.addPlayer(player);
            return player.getName() + " a rejoint la partie " + gameId;
        } else {
            return "La partie " + gameId + " n'existe pas.";
        }
    }

    @PostMapping("/games/{gameId}/start")
    public String startGame(@PathVariable String gameId) {
        Game game = gameService.getGame(gameId);
        if (game != null) {
            game.start();
            return "La partie " + gameId + " a démarré.";
        } else {
            return "La partie " + gameId + " n'existe pas.";
        }
    }

    @PostMapping("/games/{gameId}/players/{playerId}/turn")
    public String playTurn(@PathVariable String gameId ) {
        Game game = gameService.getGame(gameId);
        if (game != null) {
            Player player = game.getCurrentPlayer();
            if (player != null) {
                game.playTurn(player);
                return "Le tour de jeu de " + player.getName() + " dans la partie " + gameId + " a été effectué.";
            } else {
                return "Le joueur " +player.getId() +" n'existe pas dans la partie " + gameId + ".";
            }
        } else {
            return "La partie " + gameId + " n'existe pas.";
        }
    }
}


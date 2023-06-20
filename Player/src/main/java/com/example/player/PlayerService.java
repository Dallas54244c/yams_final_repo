package com.example.player;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerService {

    private final Map<Long, Player> playerMap;
    private List<HostingServer> servers;
    private long nextId;

    public boolean contains(long playerId) {
        return playerMap.containsKey(playerId);
    }

    public PlayerService() {
        this.playerMap = new HashMap<>();
        this.nextId = 1;
        servers = new ArrayList<>();
    }
    public void registerPlayer(long playerId, String  name) {
        Player player = new Player(playerId,name );
        playerMap.put(playerId, player);
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(playerMap.values());
    }
    public void addHostingServer(HostingServer server) {
        servers.add(server);
    }
    public List<HostingServer> getAvailableServers() {
        return servers;
    }
    public HostingServer findMatchingServer(Player player) {
        for (HostingServer server : servers) {
            if (server.isAvailable() && server.getLevel() >= player.getLevel()) {
                return server;
            }
        }
        return null;
    }
    public Player getPlayerById(long id) {
        return playerMap.get(id);
    }

    public Player createPlayer(Player player) {
        player.setId(nextId);
        player.setScore(0); // Set initial score to 0
        player.setTotalDiceRolls(0); // Set initial total dice rolls to 0
        player.setRemainingRolls(3); // Set initial remaining rolls to 3
        player.setGameStarted(false); // Set game started status to false
        playerMap.put(nextId, player);
        nextId++;
        return player;
    }










}

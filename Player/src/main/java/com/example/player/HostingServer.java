package com.example.player;

import java.util.ArrayList;
import java.util.List;

public class HostingServer {
    private final String serverId;
    private final List<Player> players;
    private boolean available;
    private int level;
    private final int maxPlayers;

    public HostingServer(String serverId, int level, int maxPlayers) {
        this.serverId = serverId;
        this.players = new ArrayList<>();
        this.available = true;
        this.level = level;
        this.maxPlayers =5;
    }

    public String getServerId() {
        return serverId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level=level;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public boolean addPlayer(Player player) {
        if (players.size() < maxPlayers && !isPlayerParticipating(player)) {
            players.add(player);
            return true;
        }
        return false;
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    public boolean isPlayerParticipating(Player player) {
        for (Player p : players) {
            if (p.getId() == player.getId()) {
                return true;
            }
        }
        return false;
    }
}


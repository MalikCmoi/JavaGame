package com.example.javagame;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerRepository {
    private static final Map<Integer, Player> PLAYERS = new ConcurrentHashMap<>();
    private static final Random RAND = new Random();
    private static volatile int currentTurn = -1;

    public Player connectPlayer(String name) {
        int id = RAND.nextInt(1000);
        Player p = new Player(id, name, 100, new int[]{0,0});
        PLAYERS.put(id, p);
        if (currentTurn < 0) {
            currentTurn = id;
        }
        return p;
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(PLAYERS.values());
    }

    public Player getPlayer(int id) {
        return PLAYERS.get(id);
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int playerId) {
        currentTurn = playerId;
    }

    public Map<Integer, Player> getMap() {
        return PLAYERS;
    }
}
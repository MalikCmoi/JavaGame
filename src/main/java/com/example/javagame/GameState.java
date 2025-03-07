package com.example.javagame;

import java.util.List;

public class GameState {
    private List<Player> players;
    private int turn;

    public GameState() {}

    public GameState(List<Player> players, int turn) {
        this.players = players;
        this.turn = turn;
    }

    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
}
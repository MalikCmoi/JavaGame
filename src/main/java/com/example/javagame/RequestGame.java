package com.example.javagame;

import java.util.List;

public class RequestGame {
    private List<Player> players;
    private int turn;

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }
    public int getTurn() { return turn; }
    public void setTurn(int turn) { this.turn = turn; }
}
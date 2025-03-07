package com.example.javagame;

public class MoveRequest {
    private int playerId;
    private int[] newPosition;
    public MoveRequest() {}
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public int[] getNewPosition() { return newPosition; }
    public void setNewPosition(int[] newPosition) { this.newPosition = newPosition; }
}

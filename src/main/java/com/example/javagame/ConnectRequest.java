package com.example.javagame;

public class ConnectRequest {
    private String playerName;
    public ConnectRequest() {}
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
}


public class MoveRequest {
    private int playerId;
    private int[] newPosition;
    public MoveRequest() {}
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public int[] getNewPosition() { return newPosition; }
    public void setNewPosition(int[] newPosition) { this.newPosition = newPosition; }
}


public class AttackRequest {
    private int playerId;
    private int targetId;
    public AttackRequest() {}
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public int getTargetId() { return targetId; }
    public void setTargetId(int targetId) { this.targetId = targetId; }
}


public class EndTurnRequest {
    private int playerId;
    public EndTurnRequest() {}
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
}


public class ChatRequest {
    private int playerId;
    private String message;
    public ChatRequest() {}
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
package com.example.javagame;

public class ChatRequest {
    private int playerId;
    private String message;
    public ChatRequest() {}
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

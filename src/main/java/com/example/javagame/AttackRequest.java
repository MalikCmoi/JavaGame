package com.example.javagame;

public class AttackRequest {
    private int playerId;
    private int targetId;
    public AttackRequest() {}
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public int getTargetId() { return targetId; }
    public void setTargetId(int targetId) { this.targetId = targetId; }
}

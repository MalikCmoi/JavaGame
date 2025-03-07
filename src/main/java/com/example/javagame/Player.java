package com.example.javagame;// Player.java


public class Player {
    private int id;
    private String name;
    private int hp;
    private int[] position;

    public Player() {}

    public Player(int id, String name, int hp, int[] position) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.position = position;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int[] getPosition() {
        return position;
    }
    public void setPosition(int[] position) {
        this.position = position;
    }
}

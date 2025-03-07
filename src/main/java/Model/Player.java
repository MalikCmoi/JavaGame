package Model;

public class Player {
    private int row;    // Ligne actuelle
    private int col;    // Colonne actuelle
    private int hp;     // Points de vie
    private int xp;     // Points d'expérience

    // Constructeur
    public Player(int row, int col, int hp, int xp) {
        this.row = row;
        this.col = col;
        this.hp = hp;
        this.xp = xp;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getHp() { return hp; }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getXp() { return xp; }
    public void setXp(int xp) {
        this.xp = xp;
    }
}
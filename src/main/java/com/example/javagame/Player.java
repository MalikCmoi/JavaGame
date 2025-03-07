public class Player {
    private String id;
    private String name;
    private int xp;
    private int health;

    public Player() {
    }

    public Player(String id, String name, int xp) {
        this.id = id;
        this.name = name;
        this.xp = xp;
    }

    // Getters / Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public void addXp(int xp) { this.xp += xp; }
    public void removeXp(int xp) { this.xp -= xp; }
    public void resetXp() { this.xp = 0; }

    public void setHealth(int health) { this.health = health; }
    public int getHealth() { return health; }
    public void decreaseHealth(int damage) { this.health -= damage; }
    public void increaseHealth(int heal) { this.health += heal; }
    public boolean isDead() { return this.health <= 0; }
}
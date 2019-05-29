import java.awt.*;

abstract public class Entity {

    //Base stats
    private double maxHealth;
    private double maxEnergy;

    //Dynamic stats
    private double health;
    private String name;
    private double energy;
    private boolean alive;

    //CREATE A GETTER FOR THIS LATER
    public Ability[] abilities;
    private int xGrid;
    private int yGrid;

    Entity(double health, String name, Ability[] abilities) {
        this.abilities = abilities;

        this.maxHealth = health;

        this.health = health;
        this.name = name;
        alive = true;
    }

    public int totalAbilities(){
        return abilities.length;
    }
    public boolean isAlive(){
        return alive;
    }
    //Will get the stored x or y location of certain entities
    public int getXGrid(){
        return xGrid;
    }

    public int getYGrid(){
        return yGrid;
    }

    //Will set the x or y location of certain entities
    public void setXGrid(int xGrid){
        this.xGrid = xGrid;
    }

    public void setYGrid(int yGrid){
        this.yGrid = yGrid;
    }

    public String getName(){
        return name;
    }

    public double getHealth(){
        return health;
    }

    public void damageEntity(double damage){
        health -= damage;
        if (health <= 0){
            alive = false;
        }
    }

    public void setHealth(double health){
        this.health = health;
    }

    public void drawIcons(Graphics g) {

    }

    public double getMaxHealth() {
        return maxHealth;
    }
}

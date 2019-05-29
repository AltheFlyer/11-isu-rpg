import java.awt.*;

abstract public class Entity {
    private double health;
    private String name;
    private double energy;
    private boolean alive;

    //CREATE A GETTER FOR THIS LATER
    public Ability ability1;
    private int xGrid;
    private int yGrid;

    Entity(double health, String name, Ability ability1) {
        this.ability1 = ability1;
        this.health = health;
        this.name = name;
        alive = true;
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
}

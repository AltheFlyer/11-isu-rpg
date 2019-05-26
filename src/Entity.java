abstract public class Entity {
    private double health;
    private String name;
    private double energy;

    //CREATE A GETTER FOR THIS LATER
    public Ability ability1;
    private int xGrid;
    private int yGrid;

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

    Entity(double health, String name, Ability ability1) {
        this.ability1 = ability1;
        this.health = health;
        this.name = name;
    }

    Entity(double health){
        this.health = health;
    }

    public String getName(){
        return name;
    }

    public double getHealth(){
        return health;
    }

    public void damageEntity(double damage){
        health -= damage;
    }

    public void setHealth(double health){
        this.health = health;
    }
}

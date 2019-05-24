abstract public class Entity {
    private double health;
    private String name;
    private double energy;
    public Ability ability1;

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

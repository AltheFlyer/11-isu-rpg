import java.util.ArrayList;

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

    private ArrayList<StatusEffect> statuses;

    Entity(double health, String name, Ability[] abilities) {
        this.abilities = abilities;

        this.maxHealth = health;

        this.health = health;
        this.name = name;
        alive = true;

        statuses = new ArrayList<StatusEffect>();
    }

    Entity(double health, double energy, String name, Ability[] abilities) {
        this.abilities = abilities;
        this.energy = energy;
        this.maxEnergy = energy;
        this.maxHealth = health;
        this.health = health;
        this.name = name;
        alive = true;

        statuses = new ArrayList<StatusEffect>();
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

    public void healEntity(double healing){
        if (health + healing >= maxHealth){
            health = maxHealth;
        } else {
            health += healing;
        }
    }

    public void setHealth(double health){
        this.health = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getEnergy(){
        return energy;
    }

    public double getMaxEnergy(){
        return maxEnergy;
    }

    public void useEnergy(double energyUsed){
        energy -= energyUsed;
    }

    public void gainEnergy(double energyGained){
        if (energy + energyGained > maxEnergy){
            energy = maxEnergy;
        } else {
            energy += energyGained;
        }
    }

    /**
     * [procStatus]
     * triggers a status effect's status, then decreases its duration by 1
     * @param map the jointmap that the game is in
     */
    public void procStatus(JointMap map) {
        for (int i = 0; i < statuses.size(); ++i) {
            statuses.get(i).triggerEffect(map, this);
            statuses.get(i).tickDuration();

            if (statuses.get(i).getDuration() <= 0) {
                //I have a feeling iterator/predicate isn't allowed so back to turing it is
                statuses.remove(i);
                i--;
            }
        }
    }


    /**
     * [dispose]
     * dereferences all objects within the player/accessing the player
     */
    public void dispose() {
        //Make all abilities null
        for (int i = 0; i < abilities.length; ++i) {
            abilities[i] = null;
        }
        statuses.clear();
    }

    /**
     * [inflictStatus]
     * Inflicts a status effect on the entity. If the entity already has a status effect of the same type, it will
     * 'stack' the effect as per the status effect's stacking behaviour. An entity will can not have more than one of
     * the same type of effect as of now.
     * @param effect the status effect to inflict
     */
    public void inflictStatus(StatusEffect effect) {
        for (int i = 0; i < statuses.size(); ++i) {
            if (statuses.get(i).getClass() == effect.getClass()) {
                statuses.get(i).stack(effect);
                return;
            }
        }
        statuses.add(effect);
    }

    /**
     * [getStatuses]
     * gets the list of statuses for the entity
     * @return ArrayList of StatusEffect, the set of statuses currently affecting the entity
     */
    public ArrayList<StatusEffect> getStatuses() {
        return statuses;
    }
}

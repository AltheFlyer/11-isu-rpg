import java.util.ArrayList;

/**
 * [Entity.java]
 * the top abstract class for all Players and Enemies
 * @version 1.0
 * @author Kevin Liu
 * @since May 23, 2019
 */
abstract public class Entity {

    //Base stats
    private double maxHealth;
    private double maxEnergy;
    private double baseAttack;
    private double baseDefence;

    //Dynamic stats
    private double health;
    private String name;
    private double energy;
    private boolean alive;
    private double attack;
    private double defence;


    //CREATE A GETTER FOR THIS LATER
    private Ability[] abilities;
    private int xGrid;
    private int yGrid;

    private ArrayList<StatusEffect> statuses;

    /**
     * Creates an enemy entity which doesn't need energy
     * @param health health of the entity
     * @param baseAttack the base attack value of the entity
     * @param baseDefence the base defence value of the entity
     * @param name the name of the entity
     * @param abilities the list of the abilities for the entity
     */
    Entity(double health, double baseAttack, double baseDefence, String name, Ability[] abilities) {
        this(health, baseAttack, baseDefence, 0, name, abilities);
    }

    /**
     * creates a player entity which has energy
     * @param health max health of the entity
     * @param baseAttack the base attack value of the entity
     * @param baseDefence the base defence value of the entity
     * @param energy the max energy that an entity has
     * @param name the name of the entity
     * @param abilities the list of the abilities for the entity
     */
    Entity(double health, double baseAttack, double baseDefence, double energy, String name, Ability[] abilities) {
        this.abilities = abilities;
        this.energy = energy;
        this.maxEnergy = energy;
        this.maxHealth = health;
        this.health = health;
        this.name = name;
        alive = true;

        this.baseAttack = baseAttack;
        this.attack = baseAttack;

        this.baseDefence = baseDefence;
        this.defence = baseDefence;

        statuses = new ArrayList<StatusEffect>();
    }

    /**
     * [totalAbilities]
     * @return abilities.length, the amount of abilities an entity has
     */
    public int totalAbilities(){
        return abilities.length;
    }


    /**
     * [endTurnLowerCooldown]
     * reduces the cooldown of all abilities by 1, for use at the end of turns
     */
    public void endTurnLowerCooldown(){
        for (int i = 0; i < abilities.length; i++){
            abilities[i].lowerCooldown(1);
        }
    }

    /**
     * Lowers the cooldown of a certain ability
     * @param index the ability to have its cooldown lowered
     * @param amount the amount of cooldown to be refunded
     */
    public void lowerCooldown(int index, int amount){
        abilities[index].lowerCooldown(amount);
    }

    /**
     * [isAlive]
     * @return alive, if an entity is alive or not
     */
    public boolean isAlive(){
        return alive;
    }

    /**
     * [getXGrid]
     * @return xGrid, the current x coord on the grid that the entity is occupying
     */
    public int getXGrid(){
        return xGrid;
    }

    /**
     * [getYGrid]
     * @return yGrid, the current y coord on the grid that the entity is occupying
     */
    public int getYGrid(){
        return yGrid;
    }

    /**
     * [setXGrid]
     * sets the current x coord on the grid that the entity is occupying
     */
    //Will set the x or y location of certain entities
    public void setXGrid(int xGrid){
        this.xGrid = xGrid;
    }

    /**
     * [setYGrid]
     * sets the current y coord on the grid that the entity is occupying
     */
    public void setYGrid(int yGrid){
        this.yGrid = yGrid;
    }

    /**
     * [getName]
     * @return name, the name of the entity
     */
    public String getName(){
        return name;
    }

    /**
     * [getHealth]
     * @return health, the current health of the entity
     */
    public double getHealth(){
        return health;
    }

    /**
     * [damageEntity]
     * damages an entity from an attack
     * @param damage the amound of damage dealt to an entity
     */
    public void damageEntity(double damage) {
        //Modify damage by defence, try to prevent negatives
        health -= damage * Math.min(0, (1.0 - defence));
        if (health <= 0){
            alive = false;
        }
    }

    /**
     * [healEntity]
     * heals an entity from a heal, cannot go over max
     * @param healing the amount of healing a person will take
     */
    public void healEntity(double healing){
        if (health + healing >= maxHealth){
            health = maxHealth;
        } else {
            health += healing;
        }
    }

    /**
     * [setHealth]
     * sets the amount of health an entity has
     * @param health the amount of healing a person will take
     */
    public void setHealth(double health){
        this.health = health;
    }

    /**
     * [getMaxHealth]
     * @return maxHealth the maximum amount of health an entity has
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * [getEnergy]
     * @return energy the current energy that an entity has
     */
    public double getEnergy(){
        return energy;
    }

    /**
     * [getMaxEnergy]
     * @return maxEnergy the maximum energy that an entity has
     */
    public double getMaxEnergy(){
        return maxEnergy;
    }

    /**
     * [useEnergy]
     * Will reduce the amount of energy that an entity has due to an attack
     * @param energyUsed the amount of energy used up from an ability
     */
    public void useEnergy(double energyUsed){
        energy -= energyUsed;
    }

    /**
     * [gainEnergy]
     * Will increase the entity's energy
     * @param energyGained the amount of energy gained from the end of the turn
     */
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

            if (statuses.get(i).getDuration() == 0) {
                statuses.remove(i);
                i--;
            }
        }
    }

    /**
     * [inflictStatus]
     * Inflicts a status effect on the entity. If the entity already has a status effect of the same type, it will
     * 'stack' the effect as per the status effect's stacking behaviour. An entity will can not have more than one of
     * the same type of effect as of now.
     * @param map, the JointMap that the entity is on
     * @param effect the status effect to inflict
     */
    public void inflictStatus(JointMap map, StatusEffect effect) {
        for (int i = 0; i < statuses.size(); ++i) {
            if (statuses.get(i).getClass() == effect.getClass()) {
                statuses.get(i).stack(effect);
                if (effect.getIsActiveImmediately()) {
                    effect.triggerEffect(map, this);
                }
                return;
            }
        }
        statuses.add(effect);
        if (effect.getIsActiveImmediately()) {
            effect.triggerEffect(map, this);
        }
    }

    /**
     * [selectRowWithMostPlayers]
     * @param map the map that all the entities are on
     * @param ability the ability that is being used
     * targets the row with the most players
     */
    public void selectRowWithMostPlayers(JointMap map, Ability ability) {
        //The abilities used are swapped from the abilities decided
        //move to row with most players
        int optimalRow = 0;
        int maxRowPlayers = 0;

        for (int y = 0; y < 3; ++y) {
            int playerCount = 0;
            for (int x = 0; x < 3; ++x) {
                if (!map.isEmpty(x, y)) {
                    playerCount++;
                    if (playerCount > maxRowPlayers) {
                        optimalRow = y;
                        maxRowPlayers = playerCount;
                    }
                }
            }
        }

        ability.indicateValidTiles(map);
        ability.action(map, getXGrid(), optimalRow);
    }

    /**
     * [getAbility]
     * will get the ability from the list for the enemy to use
     * @param index the index of the ability on the abilities list
     * @return the ability within the entity
     */
    public Ability getAbility(int index){
        return abilities[index];
    }

    /**
     * [getNumAbilities]
     * gets the number of abilities on the entity
     * @return int, the number of abilities in the entity
     */
    public int getNumAbilities() {
        return abilities.length;
    }

    /**
     * [getStatuses]
     * gets the list of statuses for the entity
     * @return ArrayList of StatusEffect, the set of statuses currently affecting the entity
     */
    public ArrayList<StatusEffect> getStatuses() {
        return statuses;
    }

    /**
     * [getBaseAttack]
     * gets the base attack of the entity
     * @return double, the base attack of the entity
     */
    public double getBaseAttack() {
        return baseAttack;
    }

    /**
     * [getBaseDefence]
     * gets the base defence of the entity as a percent: 0 = 0%, 0.5 = 50%
     * @return double, the base defence of the entity
     */
    public double getBaseDefence() {
        return baseDefence;
    }

    /**
     * [getAttack]
     * gets the current (modified) attack of the entity
     * @return double, the current attack of the enemy
     */
    public double getAttack() {
        return attack;
    }

    /**
     * [setAttack]
     * sets the attack of the entity
     * @param attack the new attack power of the entity
     */
    public void setAttack(double attack) {
        this.attack = attack;
    }

    /**
     * [getDefence]
     * gets the entity's defense (as a percent)
     * @return double, the defense of the entity
     */
    public double getDefence() {
        return defence;
    }

    /**
     * [setDefence]
     * sets the defense of the entity (as a percent between 0, 1)
     * @param defence the new defense of the entity
     */
    public void setDefence(double defence) {
        this.defence = defence;
    }

    /**
     * [isFriendly]
     * gets whether the entity is friendly or not
     * @return boolean, whether the entity is friendly or not
     */
    abstract public boolean isFriendly();
}

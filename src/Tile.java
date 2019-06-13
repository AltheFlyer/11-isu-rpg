import utils.AnimatedSprite;

import java.awt.Graphics;
import java.awt.Color;

/**
 * [Tile.java]
 * the abstract tiles that entities will be situated on
 * @version 1.0
 * @author Kevin Liu
 * @since May 23, 2019
 */
abstract public class Tile {
    private Entity entity;
    private int xGrid;
    private int yGrid;
    private int xGraphic;
    private int yGraphic;
    private boolean indicated;
    private boolean targetable;

    /**
     * Constructor for creating a Tile
     * @param xGraphic the x location in pixels, used to draw graphics
     * @param yGraphic the y location in pixels, used to draw graphics
     * @param xGrid the x location on the battle grid, used for attack ranges and calculations
     * @param yGrid the y location on the battle grid, used for attack ranges and calculations
     */
    Tile(int xGraphic, int yGraphic, int xGrid, int yGrid){
        this.xGraphic = xGraphic;
        indicated = false;
        this.yGraphic = yGraphic;
        this.xGrid = xGrid;
        this.yGrid = yGrid;
        targetable = false;
    }

    /**
     * [getXGraphic]
     * returns the x location in pixels, used mainly to draw graphics
     * @return xGraphic, the x location in pixels
     */
    public int getXGraphic(){
        return xGraphic;
    }

    /**
     * [getXGrid]
     * returns the x location on the battle grid, used for attack ranges and calculations
     * @return xGraphic, the x location on the battle grid
     */
    public int getXGrid(){
        return xGrid;
    }

    /**
     * [getYGrid]
     * returns the y location on the battle grid, used for attack ranges and calculations
     * @return yGraphic, the y location on the battle grid
     */
    public int getYGrid(){
        return yGrid;
    }

    /**
     * [getYGraphic]
     * returns the y location in pixels, used mainly to draw graphics
     * @return yGraphic, the y location in pixels
     */
    public int getYGraphic(){
        return yGraphic;
    }

    /**
     * [setEntity]
     * sets an entity in this tile location
     * @param entity the entity that is in the current tile
     */
    public void setEntity(Entity entity){
        this.entity = entity;
    }

    /**
     * [getEntity]
     * gets the entity in this tile location
     * @return entity, the entity that is in the current tile
     */
    public Entity getEntity(){
        return entity;
    }

    /**
     * [nullEntity]
     * nulls the entity in the current tile, used for if it dies or if it moves
     */
    public void nullEntity(){
        //entity.dispose();
        entity = null;
    }

    /**
     * [isEmpty]
     * Checks if a certain tile is empty or not, return true if yes, false if no
     * @return boolean, checks if the tile is empty, return true if yes, false if no
     */
    public boolean isEmpty() {
        if (entity == null) {
            return true;
        }
        return false;
    }

    /**
     * [damageTile]
     * damages the entity on a certain tile, if it kills the entity, null the entity
     * @param damage the amount of damage dealt to the entity
     */
    public void damageTile(double damage){
        if (!isEmpty()) {
            entity.damageEntity(damage);
            if (entity.getHealth() <= 0) {
                nullEntity();
            }
        }
    }

    /**
     * [healTile]
     * heals the entity on a certain tile
     * @param healing the amount of healing dealt to the entity
     */
    public void healTile(double healing) {
        if (!isEmpty()) {
            entity.healEntity(healing);
        }
    }

    /**
     * [inflictStatus]
     * adds or stacks a status effect on the entity at this tile
     * @param map the JointMap that this tile is on
     * @param effect the status effect to inflict on the entity
     */
    public void inflictStatus(JointMap map, StatusEffect effect) {
        if (!isEmpty()) {
            entity.inflictStatus(map, effect);
        }
    }

    /**
     * [procStatus]
     * triggers effects of all status effects on the entity at this tile, if there is one
     * @param map the joint map that the tile is on
     */
    public void procStatus(JointMap map) {
        if (!isEmpty()) {
            entity.procStatus(map);
        }
    }

    /**
     * [animateAttack]
     * draws an animation for a certain attack centered on a tile
     * @param g the graphics used to draw the attack
     * @param animation the animation that the attack uses it is drawn centered on this tile
     */
    public void animateAttack(Graphics g, AnimatedSprite animation){
        animation.draw(g,xGraphic-(animation.getWidth()/2-60),yGraphic-(animation.getHeight()/2-60));
    }

    /**
     * [indicate]
     * indicates if the tile is in range of an attack if it is indicated, it will differ when drawing
     */
    public void indicate() {
        indicated = true;
    }

    /**
     * [unIndicate]
     * will unindicate the tile when no attack is selected (since it will indicate based on ability ranges)
     */
    public void unIndicate() {
        indicated = false;
    }

    /**
     * [getIndication]
     * will return if a tile is indicated or not
     * @return indicated, the boolean that determines if the tile is indicated or not, used for drawing
     */
    public boolean getIndication(){
        return indicated;
    }

    /**
     * [isTargetable]
     * Is targetable means that there is a target in the location and you can target that location using your ability
     */
    public void isTargetable(){
        targetable = true;
    }

    /**
     * [unTargetable]
     * makes it so that the tile cannot be selected by an ability anymore
     */
    public void unTargetable(){
        targetable = false;
    }

    /**
     * [getTargetable]
     * will return if a tile is targetable or not
     * @return targetable, the boolean that determines if the tile is targetable or not, used for drawing or abilities
     */
    public boolean getTargetable(){
        return targetable;
    }

    /**
     * [draw]
     * draw the information present on the tile, will be overwritten
     * @param g the graphics used to draw
     */
    abstract void draw(Graphics g);

    /**
     * [isTileFriendly]
     * checks if the tile is friendly or not, will be overwritten
     */
    abstract boolean isTileFriendly();

    /**
     * [drawIcons]
     * @param g the graphics object to draw with
     */
    public void drawIcons(Graphics g) {
        drawStatus(g);
    }

    /**
     * [drawIconText]
     * @param g the graphics object to draw with
     * @param mouseX the x position of the mouse
     * @param mouseY the y position of the mouse
     */
    public void drawIconText(Graphics g, int mouseX, int mouseY) {
        drawStatusText(g, mouseX, mouseY);
    }

    /**
     * [drawStatus]
     * helper method for drawing status icons, as these should be universal to all entities
     * @param g the graphics object to draw with
     */
    public void drawStatus(Graphics g) {
        if (!isEmpty()) {
            for (int i = 0; i < getEntity().getStatuses().size(); ++i) {
                Icon icon = getEntity().getStatuses().get(i).getIcon();
                icon.setPosition(getXGraphic() + i * 40, getYGraphic() + 120 - 20);
                icon.draw(g);
            }
        }
    }

    /**
     * [drawStatusText]
     * @param g the graphics object to draw with
     * @param mouseX the x position of the mouse
     * @param mouseY the y position of the mouse
     */
    public void drawStatusText(Graphics g, int mouseX, int mouseY) {
        if (!isEmpty()) {
            for (int i = 0; i < getEntity().getStatuses().size(); ++i) {
                Icon icon = getEntity().getStatuses().get(i).getIcon();
                icon.setPosition(getXGraphic() + i * 40, getYGraphic() + 120 - 20);

                //If the mouse is hovering over the icon, draw text
                if (icon.getBoundingBox().contains(mouseX, mouseY)) {
                    icon.drawText(g, 25, 0, 150);
                }
            }
        }
    }

    /**
     * [drawHealthbar]
     * draws the health bar of the entity that is in it
     * @param g the graphics used to draw
     */
    public void drawHealthbar(Graphics g) {
        if (!isEmpty()) {
            double ratio = entity.getHealth() / entity.getMaxHealth();

            //Grey backing bar
            g.setColor(Color.GRAY);
            g.fillRect(getXGraphic(), getYGraphic() + 120 - 10, 120, 10);

            //Set healthbar color based on if friendly or not
            if (isTileFriendly()) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }

            //Draw at bottom of screen, -10 is for the height of the bar
            g.fillRect(getXGraphic(), getYGraphic() + 120 - 10, (int) (120 * ratio), 10);
            g.setColor(Color.BLACK);
            g.drawString(Math.ceil(entity.getHealth()) + "/" + entity.getMaxHealth(), getXGraphic()+30, getYGraphic() + 120);
        }
    }

    /**
     * [drawEnergybar]
     * draws the energy bar of the entity that is in it
     * @param g the graphics used to draw
     */
    public void drawEnergybar(Graphics g){
        if (!isEmpty()) {
            double ratio = entity.getEnergy() / entity.getMaxEnergy();

            //Set healthbar color based on if friendly or not
            if (isTileFriendly()) {
                //Grey backing bar
                g.setColor(Color.GRAY);
                g.fillRect(getXGraphic(), getYGraphic() + 120 - 20, 120, 10);
                g.setColor(new Color(0,200,255));
                //Draw at bottom of screen, -10 is for the height of the bar
                g.fillRect(getXGraphic(), getYGraphic() + 120 - 20, (int) (120 * ratio), 10);
                if (isTileFriendly()) {
                    g.setColor(Color.BLACK);
                    g.drawString(entity.getEnergy() + "/" + entity.getMaxEnergy(), getXGraphic() + 30, getYGraphic() + 120 - 10);
                }
            }
        }
    }

}

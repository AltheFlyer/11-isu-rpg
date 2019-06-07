import utils.AnimatedSprite;
import utils.TextDrawer;

import java.awt.*;

abstract public class Tile {
    private Entity entity;
    private Rectangle boundingBox;
    private int xGrid;
    private int yGrid;
    private int xGraphic;
    private int yGraphic;
    private boolean indicated;
    private boolean targetable;

    Tile(int xGraphic, int yGraphic, int xGrid, int yGrid){
        this.xGraphic = xGraphic;
        indicated = false;
        this.yGraphic = yGraphic;
        this.xGrid = xGrid;
        this.yGrid = yGrid;
        targetable = false;
    }

    public int getXGraphic(){
        return xGraphic;
    }

    public int getXGrid(){
        return xGrid;
    }

    public int getYGrid(){
        return yGrid;
    }

    public int getYGraphic(){
        return yGraphic;
    }


    public void setEntity(Entity entity){
        this.entity = entity;
    }

    public Entity getEntity(){
        return entity;
    }

    public void nullEntity(){
        //entity.dispose();
        entity = null;
    }

    public boolean isEmpty() {
        if (entity == null) {
            return true;
        }
        return false;
    }

    /*
    public void die(){
        entity = null;
    }
    */

    public void damageTile(double damage, int status){
        if (!isEmpty()) {
            entity.damageEntity(damage);
            if (entity.getHealth() <= 0) {
                nullEntity();
            }
        }
    }

    public void healTile(double healing, int status){
        if (!isEmpty()) {
            entity.healEntity(healing);
        }
    }

    public void animateAttack(Graphics g, AnimatedSprite animation){
        animation.draw(g,xGraphic-(animation.getWidth()/2-60),yGraphic-(animation.getHeight()/2-60));
    }


    public void indicate() {
        indicated = true;
    }
    public void unIndicate() {
        indicated = false;
    }

    public boolean getIndication(){
        return indicated;
    }

    //Is targetable means that there is a target in the location and you can target that location using your ability
    public void isTargetable(){
        targetable = true;
    }

    public void unTargetable(){
        targetable = false;
    }

    public boolean getTargetable(){
        return targetable;
    }

    abstract void draw(Graphics g);

    abstract char getType();

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
            for (int i = 0; i < getEntity().statuses.size(); ++i) {
                Icon icon = getEntity().statuses.get(i).getIcon();
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
            for (int i = 0; i < getEntity().statuses.size(); ++i) {
                Icon icon = getEntity().statuses.get(i).getIcon();
                icon.setPosition(getXGraphic() + i * 40, getYGraphic() + 120 - 20);
                icon.draw(g);

                //If the mouse is hovering over the icon, draw text
                if (icon.getBoundingBox().contains(mouseX, mouseY)) {
                    icon.drawText(g, 25, 0, 150);
                }
            }
        }
    }

    public void drawHealthbar(Graphics g) {
        if (!isEmpty()) {
            double ratio = entity.getHealth() / entity.getMaxHealth();

            //Grey backing bar
            g.setColor(Color.GRAY);
            g.fillRect(getXGraphic(), getYGraphic() + 120 - 10, 120, 10);

            //Set healthbar color based on if friendly or not
            if (getType() == 'p') {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }

            //Draw at bottom of screen, -10 is for the height of the bar
            g.fillRect(getXGraphic(), getYGraphic() + 120 - 10, (int) (120 * ratio), 10);
            g.setColor(Color.BLACK);
            g.drawString(entity.getHealth() + "/" + entity.getMaxHealth(), getXGraphic()+30, getYGraphic() + 120);
        }
    }

    public void drawEnergybar(Graphics g){
        if (!isEmpty()) {
            double ratio = entity.getEnergy() / entity.getMaxEnergy();

            //Set healthbar color based on if friendly or not
            if (getType() == 'p') {
                //Grey backing bar
                g.setColor(Color.GRAY);
                g.fillRect(getXGraphic(), getYGraphic() + 120 - 20, 120, 10);
                g.setColor(new Color(0,200,255));
                //Draw at bottom of screen, -10 is for the height of the bar
                g.fillRect(getXGraphic(), getYGraphic() + 120 - 20, (int) (120 * ratio), 10);
                if (getType() == 'p') {
                    g.setColor(Color.BLACK);
                    g.drawString(entity.getEnergy() + "/" + entity.getMaxEnergy(), getXGraphic() + 30, getYGraphic() + 120 - 10);
                }
            }
        }
    }

}

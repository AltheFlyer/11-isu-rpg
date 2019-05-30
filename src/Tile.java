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
     * @param mouseX the mouse x coordinate
     * @param mouseY the mouse y coordinate
     */
    public void drawIcons(Graphics g, int mouseX, int mouseY) {

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
                g.setColor(Color.BLUE);
                //Draw at bottom of screen, -10 is for the height of the bar
                g.fillRect(getXGraphic(), getYGraphic() + 120 - 20, (int) (120 * ratio), 10);
            }
        }
    }

}

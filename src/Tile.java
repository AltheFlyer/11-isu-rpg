import java.awt.*;

abstract public class Tile {
    private Entity entity;
    private Rectangle boundingBox;
    private int xGrid;
    private int yGrid;
    private int xGraphic;
    private int yGraphic;
    private boolean indicated;

    Tile(int xGraphic, int yGraphic, int xGrid, int yGrid){
        this.xGraphic = xGraphic;
        indicated = false;
        this.yGraphic = yGraphic;
        this.xGrid = xGrid;
        this.yGrid = yGrid;
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

    public boolean isEmpty() {
        if (entity == null) {
            return true;
        }
        return false;
    }

    public void die(){
        entity = null;
    }

    public void damageTile(double damage, int status){
        if (!isEmpty()) {
            entity.damageEntity(damage);
            if (entity.getHealth() <= 0) {
                die();
            }
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

    abstract void draw(Graphics g);

    abstract char getType();
}

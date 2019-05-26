import java.awt.*;

abstract public class Tile {
    private Entity entity;
    private Rectangle boundingBox;
    private int xTile;
    private int yTile;
    private int x;
    private int y;
    private boolean indicated;

    Tile(int x, int y){
        this.x = x;
        indicated = false;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getXTile(){
        return xTile;
    }

    public int getYTile(){
        return yTile;
    }

    public int getY(){
        return y;
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
}

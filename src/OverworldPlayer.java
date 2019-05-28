import java.awt.*;

public class OverworldPlayer extends OverworldEntity {

    public OverworldPlayer(int x, int y){
        super(x, y);
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(this.getX(),this.getY(),50,50); //modify size
    }

    public void moveUp(){ this.setY(this.getY() - 10); }

    public void moveDown(){ this.setY(this.getY() + 10); }

    public void moveLeft(){ this.setX(this.getX() - 10); }

    public void moveRight(){ this.setX(this.getX() + 10); }

}

import java.awt.*;

public class OverworldPlayer extends OverworldEntity {

    public OverworldPlayer(int x, int y){
        super(x,y);
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(this.getX(),this.getY(),10,10); //modify size
    }

    public void moveUp(){
        this.setY(this.getY() + 1);
    }

    public void moveDown(){
        this.setY(this.getY() - 1);
    }

    public void moveRight(){
        this.setX(this.getX() + 1);
    }

    public void moveLeft(){
        this.setX(this.getX() - 1);
    }

}

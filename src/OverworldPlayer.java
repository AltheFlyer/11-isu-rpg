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
        y += 1;
    }

    public void moveDown(){
        y -= 1;
    }

    public void moveRight(){ x += 1; }

    public void moveLeft(){
        x -= 1;
    }

}

import java.awt.*;

public class OverworldNPC extends OverworldEntity {

    public OverworldNPC(int x, int y){
        super(x,y);
    }

    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(this.getX(),this.getY(),50,50); //modify size
    }

}

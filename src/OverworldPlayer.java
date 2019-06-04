import java.awt.*;

/**
 * [FrameRate.java]
 * Class for player on the overworld map
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 22, 2019
 */
public class OverworldPlayer extends OverworldEntity {

    public OverworldPlayer(int x, int y){
        super(x, y);
    }

    public void draw(Graphics g, OverworldMap map) {
        g.setColor(Color.RED);
        if (map instanceof RoomMap) {
            g.fillRect(this.getX(), this.getY(), 50, 50); //modify size
        } else {
            g.fillRect(683, 384, 50, 50); //modify size
        }
    }

    public Rectangle interact() {
        Rectangle interactChecker;
        if (this.getDirection().equals("down")) {
            interactChecker = new Rectangle(this.getX(), this.getY() + this.getSize(), this.getSize(), this.getSize()/2);
        } else if (this.getDirection().equals("up")) {
            interactChecker = new Rectangle(this.getX(), this.getY() - this.getSize()/2, this.getSize(), this.getSize()/2);
        } else if (this.getDirection().equals("right")) {
            interactChecker = new Rectangle(this.getX() + this.getSize(), this.getY(), this.getSize()/2, this.getSize());
        } else { //(this.getDirection().equals("left"))
            interactChecker = new Rectangle(this.getX() - this.getSize()/2, this.getY(), this.getSize()/2, this.getSize());
        }
        return interactChecker;
    }

}

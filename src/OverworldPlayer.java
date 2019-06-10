import java.awt.*;

/**
 * [OverworldPlayer.java]
 * Class for player on the overworld map
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 22, 2019
 */
public class OverworldPlayer extends OverworldEntity {

    public OverworldPlayer(int x, int y){
        super(x, y);
    }

    /**
     * [draw]
     * draws the player sprite either in the middle for moving maps, or at its [x][y] position
     * @param g the graphics object to draw with
     * @param map the map that the player is currently in
     * @return void
     */
    public void draw(Graphics g, OverworldMap map) {
        g.setColor(Color.RED);
        if (map instanceof RoomMap) {
            g.fillRect(this.getX(), this.getY(), 50, 50); //modify size
        } else {
            g.fillRect(683, 384, 50, 50); //modify size
        }
    }

    /**
     * [interact]
     * creates an interaction hitbox in the direction the player is facing
     * @return Rectangle interactChecker, the interaction hitbox that will be used
     * to check if the player is interacting with anything
     */
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

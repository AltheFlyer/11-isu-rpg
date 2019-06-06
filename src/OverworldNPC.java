import utils.TextDrawer;

import java.awt.*;

/**
 * [OverworldNPC.java]
 * Class for NPCs on any overworld map
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 22, 2019
 */
public class OverworldNPC extends OverworldEntity {

    private String message;
    private boolean isTalking;

    public OverworldNPC(int x, int y, String message) {
        super(x,y);
        this.message = message;
        isTalking = false;
    }

    public void draw(Graphics g, OverworldMap map, OverworldPlayer player) {
        g.setColor(Color.BLUE);
        if (map instanceof RoomMap) { //regular drawing
            g.fillRect(this.getX(), this.getY(), 50, 50); //modify size
        } else { //draw NPC in relation to player location in map and moving map thing
            int xDifference = player.getX() - this.getX();
            int yDifference = player.getY() - this.getY();
            int xLocation = 683 - xDifference;
            int yLocation = 384 - yDifference;
            g.fillRect(xLocation, yLocation, 50, 50);
        }
    }

    public void speak(Graphics g) {
        TextDrawer textDrawer = new TextDrawer(g,message,150,650,1166);
        g.setColor(Color.WHITE);
        g.fillRect(100,600,1166,100);
        g.setColor(Color.BLACK);
        g.drawRect(100,600,1166,100);
        textDrawer.drawText(g);

    }

    public String getMessage() {
        return this.message;
    }

    public void setTalking() {
        this.isTalking = !(isTalking);
    }

    public boolean getTalking() {
        return isTalking;
    }

}

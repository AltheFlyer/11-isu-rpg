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
    private String name;
    private boolean isTalking;

    public OverworldNPC(int x, int y, String message, String name) {
        super(x,y);
        this.name = name;
        this.message = message;
        isTalking = false;
    }

    /**
     * [draw]
     * draws the NPC sprite either relative to the player for moving maps, or at its [x][y] position
     * @param g
     * @param map the map that the NPC is currently in
     * @return void
     */
    public void draw(Graphics g, OverworldMap map, OverworldPlayer player) {
        g.setColor(Color.BLUE);
        if (map instanceof RoomMap) { //regular drawing
            g.fillRect(this.getX(), this.getY(), 50, 50); //modify size
        } else { //draw NPC in relation to player location in map and moving map
            int xDifference = player.getX() - this.getX();
            int yDifference = player.getY() - this.getY();
            int xLocation = 683 - xDifference;
            int yLocation = 384 - yDifference;
            g.fillRect(xLocation, yLocation, 50, 50);
        }
    }

    /**
     * [speak]
     * draws the NPC's text box and speech
     * @param g
     * @return void
     */
    public void speak(Graphics g) {
        TextDrawer textDrawer = new TextDrawer(g,message,150,650,1166);
        g.setColor(Color.WHITE);
        g.fillRect(100,600,1166,100);
        g.setColor(Color.BLACK);
        g.drawRect(100,600,1166,100);
        textDrawer.drawText(g);

    }

    /**
     * [getMessage]
     * returns what the NPC says when interacted with
     * @return String message, this NPC's message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * [setTalking]
     * changes the state of the NPC from talking to not talking, or vice versa
     * @return void
     */
    public void setTalking() {
        this.isTalking = !(isTalking);
    }

    /**
     * [getTalking]
     * returns if the NPC should be talking or not
     * @return boolean isTalking, if the NPC is talking or not
     */
    public boolean getTalking() {
        return isTalking;
    }

}

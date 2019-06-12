import utils.SpriteLoader;
import utils.TextDrawer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * [OverworldNPC.java]
 * Class for NPCs on any overworld map
 * @version 1.1
 * @author Ethan Kwan
 * @since May 22, 2019
 */
public class OverworldNPC extends OverworldEntity {

    private String message;
    private String name;
    private boolean talking;
    private BufferedImage sprite;

    public OverworldNPC(int x, int y, String name, String message) {
        super(x,y);
        this.name = name;
        this.message = message;
        talking = false;
        sprite = SpriteLoader.getEntitySprite(name);
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
            //g.fillRect(this.getX(), this.getY(), 50, 50); //modify size
            g.drawImage(sprite, this.getX(), this.getY(), 50, 50, null);
        } else { //draw NPC in relation to player location in map and moving map
            int xDifference = player.getX() - this.getX();
            int yDifference = player.getY() - this.getY();
            int xLocation = 683 - xDifference;
            int yLocation = 384 - yDifference;
            //g.fillRect(xLocation, yLocation, 50, 50);
            g.drawImage(sprite, xLocation, yLocation, 50, 50, null);
        }
    }

    /**
     * [speak]
     * draws the NPC's text box and speech
     * @param g
     * @return void
     */
    public void speak(Graphics g, TextDrawer textDrawer) {
        textDrawer.setText(message);
        g.setColor(Color.WHITE);
        g.fillRect(100,600,1165,100); //fill message box
        g.fillRect(100,550,150,50); //fill name box
        g.setColor(Color.BLACK);
        g.drawRect(100,600,1165,100); //outline message box
        g.drawRect(100,550,150,50); //outline name box
        g.drawString(name,120,580); //draw name
        textDrawer.speakText(g);
        if (textDrawer.getCharactersWritten() == message.length()) {
            g.drawString("Press z to continue..",1111,675);
        }
    }


    /**
     * [checkInteractions]
     * checks if the player's interaction hitbox is intersecting with this NPC
     * if so, it prompts the NPC to show a textbox
     * also turns NPC in the direction facing the player
     * @param hitbox, the player's interaction hitbox
     * @return void
     */
    public void checkInteractions(Rectangle hitbox, String direction) {
        if (hitbox.intersects(this.collisionWindow())) {
            this.setTalking();
        }
        switch (direction) {
            case "up" : this.setDirection("down");
            break;
            case "down" : this.setDirection("up");
            break;
            case "right" : this.setDirection("left");
            break;
            case "left" : this.setDirection("right");
            break;
        }
    }

    /**
     * [checkCollisions]
     * checks if player is colliding with this NPC
     * if so, stops player from moving
     * @param playerBounds, the player's bounding box
     * @param player, the actual player
     */
    public void checkCollisions(Rectangle playerBounds, OverworldPlayer player) {
        if (playerBounds.intersects(this.collisionWindow())) {
            player.setXVelocity(0);
            player.setYVelocity(0);
        }
    }

    /**
     * [getName]
     * returns the name of the NPC
     * @return String name, the name of the NPC
     */
    public String getName() { return this.name; }

    /**
     * [setMessage]
     * sets the NPC's message to a new message
     * @param message the NPC's new message
     * @return void
     */
    public void setMessage(String message) {
        this.message = message;
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
        this.talking = !(talking);
    }

    /**
     * [isTalking]
     * returns if the NPC should be talking or not
     * @return boolean isTalking, if the NPC is talking or not
     */
    public boolean isTalking() {
        return talking;
    }

    /**
     * [shopIsOpen]
     * returns if the NPC's shop interface is open or not
     * @return boolean false, as normal NPCs never have shops
     */
    public boolean shopIsOpen() {
        return false;
    }

}

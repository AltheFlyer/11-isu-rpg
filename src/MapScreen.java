import utils.TextDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * [MapScreen.java]
 * Class for overworld map screen
 * @version 1.1
 * @author Jasmine Chu & Ethan Kwan
 * @since May 22, 2019
 */
public class MapScreen extends GameScreen {

    public Clock clock;
    private FrameRate framerate;
    private OverworldMap map;
    private OverworldPlayer player;
    private OverworldNPC[] npcs;
    private OverworldObject[] objects;
    int length;
    int width;

    public MapScreen(GameManager game, String mapPath, String walkabilityKey, String npcPath, String objectPath, int x, int y) {
        super(game);
        clock = new Clock(0.1);
        framerate = new FrameRate();
        if (mapPath.contains("moving")) {
            map = new MovingMap(getIO(), mapPath, walkabilityKey);
        } else if (mapPath.contains("physics")) {
            map = new PhysicsRoom(getIO(), mapPath, walkabilityKey);
        } else if (mapPath.contains("english")) {
            map = new EnglishRoom(getIO(), mapPath, walkabilityKey);
        } else if (mapPath.contains("chemistry")) {
            map = new ChemistryRoom(getIO(), mapPath, walkabilityKey);
        } else {
            map = new RoomMap(getIO(), mapPath, walkabilityKey);
        }
        player = new OverworldPlayer(x, y);
        npcs = getIO().getNPCs(npcPath);
        objects = getIO().getObjects(objectPath);
        length = map.getMap()[0].length;
        width = map.getMap().length;
    }

    /**
     * [paintComponent]
     * updates the clock, checks for collisions and draws the map, objects, npcs and the player
     * @param g the graphics object to draw with
     * @return void
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //updating clock and frames
        clock.update();
        framerate.update();

        //updating location of moving objects
        for (int i = 0; i < objects.length; ++i) {
            objects[i].move(clock.getElapsedTime());
        }

        //draws black background
        setBackground(Color.BLACK);

        //checking collisions with walls and NPCs
        checkCollisions();

        //drawing everything
        map.draw(g, player);
        player.draw(g, map);

        //npc management
        for (int i = 0; i < npcs.length; ++i) {
            npcs[i].draw(g, map, player); //drawing the npcs
        }
        for (int i = 0; i < npcs.length; ++i) {
            if (npcs[i].isTalking()) {
                npcs[i].speak(g);
            }
        }

        for (int i = 0; i < objects.length; ++i) {
            objects[i].draw(g);
        }

        framerate.draw(g, 10, 10);

        //ask for repaint
        repaint();
    }

    /**
     * [keyTyped]
     * checks if a certain key is typed and checks for an interaction accordingly
     * @param e key event for a pressed key
     * @return void
     */
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'z') {
            for (int i = 0; i < npcs.length; ++i) {
                npcs[i].checkInteractions(player.interact());
            }
        }
    }

    /**
     * [keyPressed]
     * checks if certain keys are pressed and changes player velocity accordingly
     * @param e key event for a pressed key
     * @return void
     */
    public void keyPressed(KeyEvent e) {
        boolean moveable = true;
        for (int i = 0; i < npcs.length; i++) {
            if (npcs[i].isTalking()) {
                moveable = false;
            }
        }
        if (moveable) {
            if (e.getKeyChar() == 'w') {
                player.setYVelocity(-5);
                player.setDirection("up");
            } else if (e.getKeyChar() == 's') {
                player.setYVelocity(5);
                player.setDirection("down");
            }
            if (e.getKeyChar() == 'd') {
                player.setXVelocity(5);
                player.setDirection("right");
            } else if (e.getKeyChar() == 'a') {
                player.setXVelocity(-5);
                player.setDirection("left");
            }
        }
    }

    /**
     * [keyReleased]
     * checks if certain keys are released and changes player velocity accordingly
     * @param e key event for a released key
     * @return void
     */
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyChar() == 'w') || (e.getKeyChar() == 's')) {
            player.setYVelocity(0);
        }
        if ((e.getKeyChar() == 'd') || (e.getKeyChar() == 'a')) {
            player.setXVelocity(0);
        }
    }

    /**
     * [checkCollisions]
     * checks the nine tiles, NPCs and objects around the player for walkability
     * stops the player from walking on a non-walkable tile
     * @return void
     */
    private void checkCollisions() {
        int playerXCenter = player.getX() + player.collisionWindow().width / 2;
        int playerYCenter = player.getY() + player.collisionWindow().height / 2;
        int centerTileX = playerXCenter / map.getTileSize();
        int centerTileY = playerYCenter / map.getTileSize();
        int playerNewX = player.calcNewX(clock.getElapsedTime());
        int playerNewY = player.calcNewY(clock.getElapsedTime());
        Rectangle playerNewBox = new Rectangle(playerNewX, playerNewY, player.getSize(), player.getSize());
        for (int i = centerTileX - 1; i < centerTileX + 2; i++) {
            for (int j = centerTileY - 1; j < centerTileY + 2; j++) {
                map.getMap()[i][j].checkCollisions(playerNewBox, player, getGame());
            }
        }
        for (int i = 0; i < npcs.length; ++i) {
            npcs[i].checkCollisions(playerNewBox, player);
        }
        for (int i = 0; i < objects.length; ++i) {
            objects[i].checkCollisions(playerNewBox, player);
        }
        player.move(clock.getElapsedTime());
    }

}

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
    private OverworldNPC[] npc;
    int length;
    int width;

    public MapScreen(GameManager game, String mapPath, String walkabilityKey, String npcPath) {
        super(game);
        clock = new Clock(0.25);
        framerate = new FrameRate();
        map = new RoomMap(getIO(), mapPath,walkabilityKey);
        player = new OverworldPlayer(400,400);
        npc = getIO().getNPCs(npcPath);
        length = map.getMap()[0].length;
        width = map.getMap().length;
    }

    /**
     * [paintComponent]
     * updates the screen
     * @param g
     * @return void
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //updating clock and frames
        clock.update();
        framerate.update();

        //draws black background
        setBackground(Color.BLACK);

        //checking collisions with walls and NPCs
        checkCollisions();

        //drawing everything
        map.draw(g, player);
        player.draw(g, map);

        //npc management
        for (int i = 0; i < npc.length; ++i) {
            npc[i].draw(g, map, player); //drawing the npcs
            if (npc[i].getTalking()) {
                npc[i].speak(g);
            }
        }

        framerate.draw(g,10,10);

        //ask for repaint
        repaint();
    }

    /**
     * [keyTyped]
     * checks if a certain key is typed and checks for an interaction accordingly
     * @param e
     * @return void
     */
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'z') {
            for (int i = 0; i < npc.length; ++i) {
                npc[i].checkInteractions(player.interact());
            }
        }
    }

    /**
     * [keyPressed]
     * checks if certain keys are pressed and changes player velocity accordingly
     * @param e
     * @return void
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w') {
            player.setYVelocity(-5);
            player.setDirection("up");
        } else if(e.getKeyChar() == 's') {
            player.setYVelocity(5);
            player.setDirection("down");
        }
        if(e.getKeyChar() == 'd') {
            player.setXVelocity(5);
            player.setDirection("right");
        } else if(e.getKeyChar() =='a') {
            player.setXVelocity(-5);
            player.setDirection("left");
        }
    }

    /**
     * [keyReleased]
     * checks if certain keys are released and changes player velocity accordingly
     * @param e
     * @return void
     */
    public void keyReleased(KeyEvent e) {
        if((e.getKeyChar() == 'w') || (e.getKeyChar() == 's')) {
            player.setYVelocity(0);
        }
        if((e.getKeyChar() == 'd') || (e.getKeyChar() =='a')) {
            player.setXVelocity(0);
        }
    }

    private void checkCollisions() {
        int playerXCenter = player.getX() + player.collisionWindow().width/2;
        int playerYCenter = player.getY() + player.collisionWindow().height/2;
        int centerTileX = playerXCenter/map.getTileSize();
        int centerTileY = playerYCenter/map.getTileSize();
        int playerNewX = player.calcNewX(clock.getElapsedTime());
        int playerNewY = player.calcNewY(clock.getElapsedTime());
        Rectangle playerNewBox = new Rectangle(playerNewX,playerNewY,player.getSize(),player.getSize());
        for (int i = centerTileX - 1; i < centerTileX + 2; i++) {
            for (int j = centerTileY - 1; j < centerTileY + 2; j++) {
                if (map.getMap()[i][j].isNotWalkable() &&
                        (map.getMap()[i][j].collisionWindow().intersects(playerNewBox))) {
                    //System.out.println("bam");
                    player.setXVelocity(0);
                    player.setYVelocity(0);
                    break;
                }
            }
        }
        for (int i = 0; i < npc.length; ++i) {
            if (playerNewBox.intersects(npc[i].collisionWindow())) {
                player.setXVelocity(0);
                player.setYVelocity(0);
                break;
            }
        }
        player.move(clock.getElapsedTime());
    }

}

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
    private OverworldNPC npc;
    int length;
    int width;

    public MapScreen(GameManager game, String mapPath, String walkabilityKey) {
        super(game);
        clock = new Clock();
        framerate = new FrameRate();
        map = new RoomMap(getIO(), mapPath,walkabilityKey);
        player = new OverworldPlayer(400,400);
        npc = new OverworldNPC(300,300, "Hey!");
        length = map.getMap().length;
        width = map.getMap()[0].length;
    }

    /**
     * [paintComponent]
     * updates the screen
     * @param g
     * @return void
     */
    public void paintComponent(Graphics g) {
        //TextDrawer textDrawer = new TextDrawer(g,npc.getMessage(),500,100,500);
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
        npc.draw(g, map, player);
        framerate.draw(g,10,10);



        //ask for repaint
        repaint();
        System.out.println(player.getX() + " " + player.getY());
    }

    /**
     * [keyPressed]
     * checks if certain keys are pressed and changes player velocity accordingly
     * also checks for interaction with non-player entities when a certain key is pressed
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
        if(e.getKeyChar() == 'z') {
            checkInteractions(player.interact());
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
                    System.out.println("bam");
                    player.setXVelocity(0);
                    player.setYVelocity(0);
                    break;
                }
            }
        }
        if (playerNewBox.intersects(npc.collisionWindow())) {
            player.setXVelocity(0);
            player.setYVelocity(0);
        } else {
            player.move(clock.getElapsedTime());
        }
    }

    private void checkInteractions(Rectangle playerBounds) {
        if (playerBounds.intersects(npc.collisionWindow())) {
            System.out.println(npc.getMessage());
        }
    }

}

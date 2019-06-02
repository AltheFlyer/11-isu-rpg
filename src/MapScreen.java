import utils.TextDrawer;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

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
        map = new MovingMap(getIO(), mapPath,walkabilityKey);
        player = new OverworldPlayer(200,200);
        npc = new OverworldNPC(300,300, "Hey!");
        length = map.getMap().length;
        width = map.getMap()[0].length;
    }

    public void paintComponent(Graphics g) {
        //private TextDrawer textDrawer = new TextDrawer(npc.getMessage(), )
        super.paintComponent(g);
        clock.update();
        framerate.update();
        setBackground(Color.BLACK);
        checkCollisions();
        map.draw(g, player);
        player.move(clock.getElapsedTime());
        player.draw(g, map);
        npc.draw(g, map, player);
        repaint();
        //System.out.println(player.getX() + " " + player.getY());
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w') {
            player.setYVelocity(-5);
            player.setDirection("up");
        }
        if(e.getKeyChar() == 's') {
            player.setYVelocity(5);
            player.setDirection("down");
        }
        if(e.getKeyChar() == 'd') {
            player.setXVelocity(5);
            player.setDirection("right");
        }
        if(e.getKeyChar() =='a') {
            player.setXVelocity(-5);
            player.setDirection("left");
        }
        if(e.getKeyChar() == 'z') {
            checkInteractions(player.interact());
        }
    }

    public void keyReleased(KeyEvent e) {
        if((e.getKeyChar() == 'w') || (e.getKeyChar() == 's')) {
            player.setYVelocity(0);
        }
        if((e.getKeyChar() == 'd') || (e.getKeyChar() =='a')) {
            player.setXVelocity(0);
        }
    }

    private void checkCollisions() {
        //if (player.collisionWindow().intersects(npc.collisionWindow())) {
        //    collide();
        //}
        int playerXCenter = player.getX() + player.collisionWindow().width/2;
        int playerYCenter = player.getY() + player.collisionWindow().height/2;
        int centerTileX = playerXCenter/map.getTileSize();
        int centerTileY = playerYCenter/map.getTileSize();
        for (int i = centerTileX - 1; i < centerTileX + 2; i++) {
            for (int j = centerTileY - 1; j < centerTileY + 2; j++) {
                if (map.getMap()[i][j].isNotWalkable() &&
                        (map.getMap()[i][j].collisionWindow().intersects(player.collisionWindow()))) {
                    collide(map.getMap()[i][j]);
                }
            }
        }
    }

    private void collide(OverworldTile tile) {
        player.setXVelocity(0);
        player.setYVelocity(0);
        if (player.getDirection().equals("down")) {
            player.setY(tile.getY() - map.getTileSize());
        } else if (player.getDirection().equals("up")) {
            player.setY(tile.getY() + map.getTileSize());
        } else if (player.getDirection().equals("right")) {
            player.setX(tile.getX() - map.getTileSize());
        } else if (player.getDirection().equals("left")) {
            player.setX(tile.getX() + map.getTileSize());
        }
    }

    private void checkInteractions(Rectangle playerBounds) {
        if (playerBounds.intersects(npc.collisionWindow())) {
            System.out.println(npc.getMessage());
        }
    }

}

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MapScreen extends GameScreen {

    private OverworldMap map;
    private OverworldPlayer player;
    private OverworldNPC npc;
    int length;
    int width;

    public MapScreen(GameManager game, String mapPath, String walkabilityKey) {
        super(game);
        map = new MovingMap(getIO(), mapPath,walkabilityKey);
        player = new OverworldPlayer(200,200);
        npc = new OverworldNPC(300,300);
        length = map.getMap().length;
        width = map.getMap()[0].length;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        checkCollisions();
        map.draw(g, player);
        player.draw(g, map);
        npc.draw(g, map, player);
        repaint();
        System.out.println(player.getX() + " " + player.getY());
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w') {
            player.moveUp();
            player.setDirection("up");
        }
        if(e.getKeyChar() == 's') {
            player.moveDown();
            player.setDirection("down");
        }
        if(e.getKeyChar() == 'd') {
            player.moveRight();
            player.setDirection("right");
        }
        if(e.getKeyChar() =='a') {
            player.moveLeft();
            player.setDirection("left");
        }
        if(e.getKeyChar() == 'z') {
            System.out.println("interact!");
        }
    }

    private void checkCollisions() {
        if (player.collisionWindow().intersects(npc.collisionWindow())) {
            collide();
        }
        int playerXCenter = player.getX() + player.collisionWindow().width/2;
        int playerYCenter = player.getY() + player.collisionWindow().height/2;
        int centerTileX = playerXCenter/map.getTileSize();
        int centerTileY = playerYCenter/map.getTileSize();
        for (int i = centerTileX - 1; i < centerTileX + 2; i++) {
            for (int j = centerTileY - 1; j < centerTileY + 2; j++) {
                if (map.getMap()[i][j].isNotWalkable() &&
                        (map.getMap()[i][j].collisionWindow().intersects(player.collisionWindow()))) {
                    collide();
                }
            }
        }
    }

    private void collide() {
        if (player.getDirection().equals("down")) {
            player.moveUp();
        } else if (player.getDirection().equals("up")) {
            player.moveDown();
        } else if (player.getDirection().equals("right")) {
            player.moveLeft();
        } else if (player.getDirection().equals("left")) {
            player.moveRight();
        }
    }

}

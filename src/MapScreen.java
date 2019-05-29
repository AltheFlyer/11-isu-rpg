import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MapScreen extends GameScreen {

    private OverworldMap map;
    private OverworldPlayer player;
    private OverworldNPC rando;
    int length;
    int width;

    public MapScreen(GameManager game, String mapPath, String walkabilityKey){
        super(game);
        map = new RoomMap(mapPath,walkabilityKey);
        player = new OverworldPlayer(500,500);
        //rando = new OverworldNPC((int)(Math.random()*map.getTileSize()*length),
        //        (int)(Math.random()*map.getTileSize()*width));
        length = map.getMap().length;
        width = map.getMap()[0].length;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        checkCollisions();
        map.draw(g, player);
        player.draw(g, map);
//        rando.draw(g);
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w'){
            player.moveUp();
        }
        if(e.getKeyChar() == 's'){
            player.moveDown();
        }
        if(e.getKeyChar() == 'd'){
            player.moveRight();
        }
        if(e.getKeyChar() =='a'){
            player.moveLeft();
        }
        //System.out.println(player.getX());
        //System.out.println(player.getY());
    }
    private void checkCollisions(){
        int playerXCenter = player.getX() + player.collisionWindow().width/2;
        int playerYCenter = player.getY() + player.collisionWindow().height/2;
        int centerTileX = playerXCenter/map.getTileSize();
        int centerTileY = playerYCenter/map.getTileSize();
        for (int i = centerTileX - 1; i < centerTileX + 2; i++) {
            for (int j = centerTileY - 1; j < centerTileY + 2; j++) {
                if (map.getMap()[i][j].isNotWalkable() &&
                        (map.getMap()[i][j].collisionWindow().intersects(player.collisionWindow()))) {
                    System.out.println("bam");
                    //reject(playerXCenter,playerYCenter,map.getMap()[i][j]);
                }
            }
        }

    }
    private void reject(int playerX, int playerY, OverworldTile tile){
        if (playerX < tile.getX()*map.getTileSize()) {
            player.setX(tile.getX()*map.getTileSize() - map.getTileSize());
        }
        if (playerX > tile.getX()*map.getTileSize()) {
            player.setX(tile.getX()*map.getTileSize() + map.getTileSize());
        }
        if (playerY < tile.getY()*map.getTileSize()) {
            player.setY(tile.getY()*map.getTileSize() - map.getTileSize());
        }
        if (playerY > tile.getY()*map.getTileSize()) {
            player.setY(tile.getY()*map.getTileSize() + map.getTileSize());
        }
    }
}

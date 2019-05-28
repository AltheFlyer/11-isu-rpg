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
        map = new MovingMap(mapPath,walkabilityKey);
        player = new OverworldPlayer((int)(Math.random()*map.getTileSize()*length),
                (int)(Math.random()*map.getTileSize()*width));
        rando = new OverworldNPC((int)(Math.random()*map.getTileSize()*length),
                (int)(Math.random()*map.getTileSize()*width));
        length = map.getMap().length;
        width = map.getMap()[0].length;
    }

    public void paintComponent(Graphics g, OverworldPlayer player){
        super.paintComponent(g);
        map.draw(g);
     //   checkCollisions(player);
        player.draw(g);
        rando.draw(g);
        repaint();
    }

    public void keyTyped(KeyEvent e) {
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
        //OverworldTile[][] collisionTiles = new OverworldTile[3][3];
        int playerXCenter = player.getX() + player.interactionPane().width/2;
        int playerYCenter = player.getY() + player.interactionPane().height/2;
        int centerTileX = playerXCenter/map.getTileSize();
        int centerTileY = playerYCenter/map.getTileSize();
        for (int i = centerTileX - 1; i < centerTileX + 2; i++) {
            for (int j = centerTileY - 1; j < centerTileY + 2; j++) {

                if (player.interactionPane().intersects(map.getMap()[i][j].collisionWindow)&&
                !(map.getMap()[i][j].isWalkable())) {
                    reject(playerXCenter,playerYCenter,map.getMap()[i][j]);
                 }

                //collisionTiles[i - centerTileX + 1][j - centerTileY + 1] = map.getMap()[i][j];
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

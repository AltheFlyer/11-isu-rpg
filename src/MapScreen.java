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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        map.draw(g, player);
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
/**
    public void checkCollisions(OverworldPlayer player){
        int playerX = player.getX();
        int playerY = player.getY();

    }
*/
}

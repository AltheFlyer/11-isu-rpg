import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MapScreen extends GameScreen {

    private OverworldMap map;
    private OverworldPlayer player;
    private OverworldNPC rando;

    MapScreen(GameManager game, OverworldMap map){
        super(game);
        this.map = map;
        player = new OverworldPlayer(25,25);
        rando = new OverworldNPC(13,43);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        map.draw(g);
        player.draw(g);
        rando.draw(g);
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == e.VK_UP){
            player.moveUp();
        }
        if(e.getKeyCode() == e.VK_DOWN){
            player.moveDown();
        }
        if(e.getKeyCode() == e.VK_RIGHT){
            player.moveRight();
        }
        if(e.getKeyCode() == e.VK_LEFT){
            player.moveLeft();
        }
    }

}

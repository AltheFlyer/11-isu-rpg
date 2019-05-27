import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MapScreen extends GameScreen {

    private OverworldMap map;
    private OverworldPlayer player;
    private OverworldNPC rando;

    MapScreen(GameManager game, OverworldMap map){
        super(game);
        map = new OverworldMap("five_by_five_map.txt");
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
    }

}

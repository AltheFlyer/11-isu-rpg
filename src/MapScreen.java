import java.awt.*;

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
}

import java.awt.*;

public class LoadingScreen extends GameScreen {

    public LoadingScreen(GameManager game) {
        super(game);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawString("Now loading...",10,10);

        repaint();
    }

}

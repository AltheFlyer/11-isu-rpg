package utils;

import javax.swing.*;
import java.awt.*;

public class TextTester {

    public static void main(String[] ags) {
        JFrame window = new JFrame();

        //Default window values
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Smallest window settings for fullscreen (Allen's PC)
        window.setSize(1366, 768);
        window.setVisible(true);

        window.add(new TextPanel());
    }

    static class TextPanel extends JPanel {

        TextDrawer t;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (t == null) {
                t = new TextDrawer(g, "a b c d e f g h i j k l m n o p q r s t u v w x y z", 10, 10, 200);
            }

            t.drawText(g);
            repaint();
        }
    }
}

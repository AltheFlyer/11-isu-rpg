package utils;

import javax.swing.*;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

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
        SpecialTextDrawer st;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (t == null) {
                t = new TextDrawer(g, "Hey guys, it's good to know that text drawing works right now!\nNext stop, *special* text drawing!", 10, 10, 200, 50);
                AttributedString as = new AttributedString("Hello WORLD, Hey Vsauce! Michael here. 2 3 5 7 11 13 17 19 23 ... ewfhbejgskyhbvekhrekbrkgshrebeskhrg ");
                as.addAttribute(TextAttribute.BACKGROUND, Color.RED, 10, 20);
                as.addAttribute(TextAttribute.FONT, new Font("Comic Sans MS", Font.BOLD, 36), 0, 15);
                as.addAttribute(TextAttribute.FONT, new Font("Wingdings", Font.PLAIN ,100), 50, 60);
                st = new SpecialTextDrawer(g, as, 220, 10, 150);
            }

            t.speakText(g);
            st.drawText(g);
            repaint();
        }
    }
}

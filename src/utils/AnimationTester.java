package utils;

import javax.swing.*;
import java.awt.*;

public class AnimationTester {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        //Default window values
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Smallest window settings for fullscreen (Allen's PC)
        window.setSize(1366, 768);

        window.add(new AnimationPanel());

        window.setVisible(true);
    }

    static class AnimationPanel extends JPanel {

        AnimatedSprite gif;
        AnimatedSprite gif2;

        public AnimationPanel() {
            gif = new AnimatedSprite("spritesheets/JFrames.png", 1, 10, 40, 40, 100);
            gif2 = new AnimatedSprite("spritesheets/JFrames.png", 1, 10, 40, 40, 50);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            gif.draw(g, 100, 100);
            gif2.draw(g, 140, 100);

            repaint();
        }
    }
}

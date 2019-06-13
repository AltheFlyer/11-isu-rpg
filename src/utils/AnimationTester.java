package utils;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;

/**
 * [AnimationTester]
 * OLD file to test animated sprite in its inception
 * @version 1.0
 * @author Allen Liu
 * @since May 26, 2019
 */
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

    /**
     * [AnimationPanel]
     * used to draw animated sprite contents for testing
     */
    static class AnimationPanel extends JPanel {

        AnimatedSprite gif;
        AnimatedSprite gif2;

        public AnimationPanel() {
            gif = new AnimatedSprite("spritesheets/JFrames.png", 1, 10, 100);
            gif2 = new AnimatedSprite("spritesheets/JFrames.png", 1, 10, 50);
        }

        /**
         * [paintComponent]
         * paints animated sprite tests
         * @param g the graphics objects to draw with
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            gif.draw(g, 100, 100);
            gif2.draw(g, 140, 100);

            repaint();
        }
    }
}

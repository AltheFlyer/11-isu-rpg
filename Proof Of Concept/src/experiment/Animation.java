package experiment;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animation {

    public static void main(String[] args) throws IOException {


        JFrame window = new JFrame();
        window.setSize(400, 400);

        window.add(new AnimationPane());

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);
    }

    static class AnimationPane extends JPanel implements MouseListener {

        BufferedImage[] sprites;

        int frame;

        double time;

        public AnimationPane() {
            BufferedImage sheet = null;
            try {
                sheet = ImageIO.read(new File("src/experiment/JFrames.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            sprites = new BufferedImage[10];
            int width = 40;
            int height = 40;

            for (int i = 0; i < 10; ++i) {
                sprites[i] = sheet.getSubimage(i * width, 0, width, height);
            }

            frame = 0;

            addMouseListener(this);

            time = System.currentTimeMillis();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(sprites[frame], 0, 0, 400, 400, this);

            if (System.currentTimeMillis() - time > 100) {
                time = System.currentTimeMillis();
                if (frame != 9) {
                    ++frame;
                } else {
                    frame = 0;
                }
            }

            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (frame != 9) {
                ++frame;
            } else {
                frame = 0;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}

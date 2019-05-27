import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GlassPaneTest {

    public static void main(String[] args) {
        JFrame window = new JFrame("Glass test");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1366, 768);

        window.getContentPane().add(new BasicPanel());


        window.setVisible(true);

        Glass gl = new Glass();
        window.setGlassPane(gl);

        window.revalidate();

        System.out.println(window.getGlassPane().toString());
    }

    static class Glass extends JComponent implements MouseListener {

        public Glass() {
            addMouseListener(this);

            System.out.println("FORM");
        }


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("GLASS");
            e.consume();
            dispatchEvent(e);
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

    static class BasicPanel extends JPanel implements MouseListener {


        public BasicPanel() {
            addMouseListener(this);
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.GREEN);
            g.fillRect(100, 100, 100, 100);

            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Yeah");
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

package experiment;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;

public class SpecialTextDrawer {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.add(new DrawPanel());

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setVisible(true);
    }

    static class DrawPanel extends JPanel {

        Font font;

        DrawPanel() {
            Map<TextAttribute, Object> map = new HashMap<TextAttribute, Object>();

            map.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
            font = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
            font = font.deriveFont(map);
            System.out.println(font.getName());
            System.out.println(font.getStyle());
            System.out.println(font.getSize());

        }

        @Override
        public void paintComponent(Graphics g) {
            g.setFont(font);

            Graphics2D g2d = (Graphics2D) g;

            AttributedString text = new AttributedString("If you have a paragraph of styled text that you would like to fit within a specific width, you can use the LineBreakMeasurer class. This class enables styled text to be broken into lines so that they fit within a particular visual advance. Each line is returned as a TextLayout object, which represents unchangeable, styled character data. However, this class also enables access to layout information. The getAscent and getDescent methods of TextLayout return information about the font that is used to position the lines in the component. The text is stored as an AttributedCharacterIterator object so that the font and point size attributes can be stored with the text.");

            text.addAttribute(TextAttribute.FOREGROUND, Color.RED, 0, 10);
            AttributedCharacterIterator charIter = text.getIterator();

            int textLength = charIter.getEndIndex();

            FontRenderContext context = g2d.getFontRenderContext();
            LineBreakMeasurer lineMeasure = new LineBreakMeasurer(charIter, context);

            lineMeasure.setPosition(0);

            int xPos = 50;
            int yPos = 50;

            while (lineMeasure.getPosition() < textLength) {
                TextLayout layout = lineMeasure.nextLayout(300);

                layout.draw(g2d, xPos, yPos);
                yPos += layout.getDescent() + layout.getLeading() + layout.getAscent();
            }

            //g.drawString("Hello World", 100, 100);
        }
    }
}

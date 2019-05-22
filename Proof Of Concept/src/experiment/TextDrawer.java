package experiment;

import javax.swing.*;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class TextDrawer {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Text time");

        frame.add(new DrawPanel());

        frame.setSize(400, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static class DrawPanel extends JPanel{

        ArrayList<String> textLines = new ArrayList<String>();
        String output;
        String displayed;

        @Override
        public void paintComponent(Graphics g) {
            TextParse thing = new TextParse();

            //thing.drawText(g, "a a a a \n a a a a \n a a aa a a a <color:#00ff00> aa <color:#000000> a a a aaaa a aa a a aa a aa a a a  a a a a a a a a aaaaa a a a a a               a a a aa a a a a a a", 10, 10, 300, 300);

            TextParse t = new TextParse();
            t.setTextLines(g, "aaa <color:#dddddd> aaa aaa");

            g.drawRect(10, 10, 100, 100);

            /*
            FontMetrics fontData = g.getFontMetrics();

            String toDraw = "The FontMetrics class defines a font metrics object, which encapsulates information about the rendering of a particular font on a particular screen.\n" +
                    "Note to subclassers: Since many of these methods form closed, mutually recursive loops, you must take care that you implement at least one of the methods in each such loop to prevent infinite recursion when your subclass is used. In particular, the following is the minimal suggested set of methods to override in order to ensure correctness and prevent infinite recursion (though other subsets are equally feasible):";

            int width = 0;
            int MAX_WIDTH = 250;
            int maxHeight = fontData.getMaxAscent() + fontData.getMaxDescent();
            int lines = 0;

            if (textLines.size() == 0) {
                textLines.add("");
                while (toDraw.length() > 0) {
                    //System.out.println(toDraw);
                    int spaceIndex = toDraw.indexOf(" ");
                    String word;
                    if (spaceIndex != -1) {
                        word = toDraw.substring(0, toDraw.indexOf(" ") + 1);
                        toDraw = toDraw.substring(toDraw.indexOf(" ") + 1);
                    } else {
                        word = toDraw;
                        toDraw = "";
                    }
                    int wordWidth = (int) fontData.getStringBounds(word, g).getWidth();
                    if (wordWidth + width > MAX_WIDTH) {
                        lines++;
                        textLines.add("");
                        width = 0;
                    }

                    textLines.set(lines, textLines.get(lines) + word);
                    //g.drawString(word, 10 + width, 100 + lines * maxHeight);

                    width += wordWidth;
                }
            }

            for (int i = 0; i < textLines.size(); ++i) {
                g.drawString(textLines.get(i), 10, 100 + i * maxHeight);
            }
            */
        }
    }
}

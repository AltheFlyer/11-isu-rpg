package experiment;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 *
 * HIewufhureigherhgerg
 */
public class TextParse {

    Color currentColor;

    public void drawText(Graphics g, String text, int xStart, int yStart, int widthConstrain, int heightConstrain) {
        FontMetrics fontData = g.getFontMetrics();

        int xMarker = xStart;
        int yMarker = yStart;
        int widthMarker = 0;

        int lineHeight = fontData.getAscent() + fontData.getDescent() + fontData.getLeading();

        while (text.length() > 0) {
            int spaceIndex = text.indexOf(" ");
            int newLineIndex = text.indexOf("\n");

            String word = "";

            if (newLineIndex != -1 && newLineIndex < spaceIndex) {
                word = text.substring(0, newLineIndex + 2);
                text = text.substring(newLineIndex + 2);
            } else if (spaceIndex == -1) {
                word = text;
                text = "";
            } else {
                word = text.substring(0, spaceIndex + 1);
                text = text.substring(spaceIndex + 1);
            }

            //Parsing time
            //Check to change color
            if (word.length() == 16 && word.substring(0, 8).equals("<color:#") && word.charAt(14) == '>') {
                setColor(g, word.substring(7, 14));
                System.out.println(word.substring(7, 14));
            } else {
                //Draw word if not a special string
                int wordWidth = (int) fontData.getStringBounds(word, g).getWidth();

                if (widthMarker + wordWidth > widthConstrain) {
                    xMarker = xStart;
                    widthMarker = 0;
                    yMarker += lineHeight;
                }

                g.drawString(word, xMarker, yMarker);
                xMarker += wordWidth;
                widthMarker += wordWidth;
            }
            if (word.contains("\n")) {
                xMarker = xStart;
                widthMarker = 0;
                yMarker += lineHeight;
            }
        }

        System.out.println();
    }

    private void setColor(Graphics g, String s) {
        currentColor = Color.decode(s);
        g.setColor(currentColor);
    }

    public void setTextLines(Graphics g, String text) {
        System.out.println(text);
        AttributedString attrString;

        //Generate attributed string
        String newText = "";
        while (text.length() > 0) {
            String word = "";

            int spaceIndex = text.indexOf(" ");

            if (spaceIndex == -1) {
                word = text;
                text = "";
            } else {
                word = text.substring(0, spaceIndex + 1);
                text = text.substring(spaceIndex + 1);
            }

            if (word.length() >= 14) {
                System.out.println(word.substring(0, 7));
                if (word.substring(0, 7).equals("<color:")) {
                    word = "";
                }
            }
            newText += word;
        }
        attrString = new AttributedString(newText);

        AttributedCharacterIterator charIter;

        attrString.addAttribute(TextAttribute.FONT, new Font("Comic Sans MS", Font.ITALIC, 24));

        System.out.println(newText);
        g.drawString(attrString.getIterator(), 10, 10);
    }

}

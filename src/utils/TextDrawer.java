package utils;

import java.awt.*;
import java.util.ArrayList;

/**
 * [TextDrawer.java]
 * Draws multiline text onto a JPanel, and saves the line breaks for continuous drawing
 * @version 0.1
 * @author Allen Liu
 * @since May 22, 2019
 */
public class TextDrawer {

    String[] lines;
    int x, y;
    int lineHeight;

    public TextDrawer(Graphics g, String text, int x, int y, int maxWidth) {
        FontMetrics fontData = g.getFontMetrics();
        this.x = x;
        this.y = y;

        lineHeight = fontData.getAscent() + fontData.getLeading() + fontData.getDescent();

        this.generateTextLines(g, text, maxWidth);
    }

    /**
     * [drawText]
     * Draws the text across multiple lines
     * @param g The graphics object to draw with
     */
    public void drawText(Graphics g) {
        for (int i = 0; i < lines.length; ++i) {
            g.drawString(lines[i], x, y + lineHeight * i);
        }
    }

    /**
     * [generateTextLines]
     * Takes in a string and breaks it apart into lines
     * @param g The graphics object to draw with
     * @param text The text to draw
     * @param maxWidth The maximum width (wrapping widt) of a line of text
     */
    private void generateTextLines(Graphics g, String text, int maxWidth) {
        FontMetrics fontData = g.getFontMetrics();
        ArrayList<String> tempLines = new ArrayList<>();

        int widthMarker = 0;

        String line = "";

        while (text.length() > 0) {
            int spaceIndex = text.indexOf(" ");
            int newLineIndex = text.indexOf("\n");

            String word;

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

            int wordWidth = (int) fontData.getStringBounds(word, g).getWidth();

            if (widthMarker + wordWidth > maxWidth) {
                widthMarker = 0;
                tempLines.add(line);
                line = "";
            }

            line += word;

            widthMarker += wordWidth;
        }

        //Final incomplete line
        tempLines.add(line);

        lines = new String[tempLines.size()];

        for (int i = 0; i < tempLines.size(); ++i) {
            lines[i] = tempLines.get(i);
        }
    }

}

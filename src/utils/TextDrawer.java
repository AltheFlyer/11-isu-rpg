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

    /**
     * [TextDrawer]
     * Use this for generic text with the text set by a graphics object.
     * @param g the graphics object to draw with
     * @param text the text to draw
     * @param x the x position to draw from
     * @param y the y position to draw from
     * @param maxWidth the maximum width of a line of text (this is NOT enforced by words that exceed this width)
     */
    public TextDrawer(Graphics g, String text, int x, int y, int maxWidth) {
        FontMetrics fontData = g.getFontMetrics();
        this.x = x;
        this.y = y;

        //This is: the height above baseline, the height below baseline, and the font's recommended spacing between lines
        lineHeight = fontData.getAscent() + fontData.getDescent() + fontData.getLeading();

        this.generateTextLines(g, text, maxWidth);
    }

    /**
     * [drawText]
     * Draws the text across multiple lines.
     * @param g The graphics object to draw with
     */
    public void drawText(Graphics g) {
        for (int i = 0; i < lines.length; ++i) {
            g.drawString(lines[i], x, y + lineHeight * i);
        }
    }

    /**
     * [generateTextLines]
     * Takes in an unstyled string and breaks it apart into lines
     * @param g The graphics object to draw with
     * @param text The text to draw
     * @param maxWidth The maximum width (wrapping widt) of a line of text
     */
    private void generateTextLines(Graphics g, String text, int maxWidth) {
        //Font data
        FontMetrics fontData = g.getFontMetrics();
        //Stores the list of lines
        ArrayList<String> tempLines = new ArrayList<>();

        int widthMarker = 0;

        String line = "";

        while (text.length() > 0) {
            //Find the closest space and newline character
            int spaceIndex = text.indexOf(" ");
            int newLineIndex = text.indexOf("\n");

            //The next word in the sentence
            String word;

            //Create the word by breaking at the nearest space or newline
            if ((newLineIndex != -1) && (newLineIndex < spaceIndex)) {
                word = text.substring(0, newLineIndex + 2);
                text = text.substring(newLineIndex + 2);
            } else if (spaceIndex == -1) {
                word = text;
                text = "";
            } else {
                word = text.substring(0, spaceIndex + 1);
                text = text.substring(spaceIndex + 1);
            }

            //Get width of the word with the current graphics
            int wordWidth = (int) fontData.getStringBounds(word, g).getWidth();

            //If width of word would overflow, move to another line
            if (widthMarker + wordWidth > maxWidth) {
                widthMarker = 0;
                tempLines.add(line);
                line = "";
            }

            //Add word to the current line
            line += word;

            //Increment the width marker for the current line
            widthMarker += wordWidth;
        }

        //Final incomplete line
        tempLines.add(line);

        //Create array of strings, one string for each line
        lines = new String[tempLines.size()];

        for (int i = 0; i < tempLines.size(); ++i) {
            lines[i] = tempLines.get(i);
        }
    }


}

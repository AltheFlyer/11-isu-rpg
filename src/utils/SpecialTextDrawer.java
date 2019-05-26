package utils;

import java.awt.*;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;

/**
 * [SpecialTextDrawer.java]
 * Draws multiline text with options for styled text, based off of logic from TextDrawer.
 * Speech like text drawing is NOT available due to limitations in AttributedString and associated objects.
 * @version 1.0
 * @author Allen Liu
 * @since May 26, 2019
 */
public class SpecialTextDrawer {

    TextLayout[] lines;
    int x, y;

    String plainText;

    /**
     * [SpecialTextDrawer]
     * Creates a special text drawerthat can draw using text attributes
     * @param g the graphics object to draw with
     * @param text the text to draw
     * @param x the x position of the baseline of the text
     * @param y the y position of the baseline of the text
     * @param maxWidth the maximum width of the text lines
     */
    public SpecialTextDrawer(Graphics g, String text, int x, int y, int maxWidth) {
        generateLines(g, new AttributedString(text), maxWidth);
        this.x = x;
        this.y = y;
        plainText = text;
    }

    /**
     * [SpecialTextDrawer]
     * Creates a special text drawerthat can draw using text attributes
     * @param g the graphics object to draw with
     * @param text the attributed text to draw
     * @param x the x position of the baseline of the text
     * @param y the y position of the baseline of the text
     * @param maxWidth the maximum width of the text lines
     */
    public SpecialTextDrawer(Graphics g, AttributedString text, int x, int y, int maxWidth) {
        generateLines(g, text, maxWidth);
        this.x = x;
        this.y = y;
    }

    /**
     * [generateLines]
     * generates text lines as an array of TextLayout objects
     * @param g the graphics object to draw with
     * @param s the attributed string to convert into lines
     * @param maxWidth the maximum width of a line
     */
    private void generateLines(Graphics g, AttributedString s, int maxWidth) {
        ArrayList<TextLayout> textLines = new ArrayList<>();

        //Create character iterator (which allows interaction with characters within)
        AttributedCharacterIterator charIter = s.getIterator();

        //Create line break measurer, which will automatically break lines
        LineBreakMeasurer lineMeasure = new LineBreakMeasurer(charIter, ((Graphics2D) g).getFontRenderContext());

        while (lineMeasure.getPosition() < charIter.getEndIndex()) {
            textLines.add(lineMeasure.nextLayout(maxWidth));
        }

        lines = new TextLayout[textLines.size()];

        for (int i = 0; i < textLines.size(); ++i) {
            lines[i] = textLines.get(i);
        }
    }

    /**
     * [drawText]
     * draws the saved attributed text as a set of multiple lines
     * @param g the graphics object to draw with
     */
    public void drawText(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int yPosition = y;
        for (int i = 0; i < lines.length; ++i) {
            //Since lines can have varying height, use the current line's ascent, and the previous line's descent + leading
            yPosition += lines[i].getAscent();
            lines[i].draw(g2d, x, yPosition);
            yPosition += lines[i].getDescent() + lines[i].getLeading();
        }
    }
}

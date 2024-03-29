package utils;

import java.awt.Graphics;
import java.awt.FontMetrics;

import java.util.ArrayList;

/**
 * [TextDrawer.java]
 * Draws multiline text onto a JPanel, and saves the line breaks for continuous drawing.
 * Instructions on use: Create TextDrawer object within paintComponent so access to a valid Graphics object is given.
 * Use draw() to draw text without needing to redo text width calculations.
 * THIS METHOD ONLY WORKS BASED ON THE FONT GIVEN BY THE GRAPHICS OBJECT, if the font changes, a new TextDrawer must
 * be made.
 * @version 1.0
 * @author Allen Liu
 * @since May 22, 2019
 */
public class TextDrawer {

    private String[] lines;
    private int x, y;
    private int lineHeight;

    //Spoken text values
    private int charactersWritten;
    private long characterDelay;
    private long lastTextUpdate;

    private int textLength;
    private int maxWidth;

    private FontMetrics fontData;

    /**
     * [TextDrawer]
     * Use this for generic text with the text set by a graphics object.
     * @param g the graphics object to draw with
     * @param text the text to draw
     * @param x the x position of the baseline of the text
     * @param y the y position of the baseline of the text
     * @param maxWidth the maximum width of a line of text in pixels (this is NOT enforced by words that exceed this width)
     */
    public TextDrawer(Graphics g, String text, int x, int y, int maxWidth) {
        fontData = g.getFontMetrics();
        this.maxWidth = maxWidth;
        this.x = x;
        this.y = y;

        //This is: the height above baseline, the height below baseline, and the font's recommended spacing between lines
        lineHeight = fontData.getAscent() + fontData.getDescent() + fontData.getLeading();

        this.generateTextLines(text, maxWidth);

        charactersWritten = 0;
        textLength = text.length();
    }

    /**
     * [TextDrawer]
     * Use this for generic text with the text set by a graphics object, where the text is displayed as it it were 'spoken'
     * @param g the graphics object to draw with
     * @param text the text to draw
     * @param x the x position of the baseline of the text
     * @param y the y position of the baseline of the text
     * @param maxWidth the maximum width of a line of text in pixels (this is NOT enforced by words that exceed this width)
     * @param characterDelay the delay in milliseconds between each character being drawn (this only has an effect when
     *                       the draw() method is called multiple times)
     */
    public TextDrawer(Graphics g, String text, int x, int y, int maxWidth, int characterDelay) {
        fontData = g.getFontMetrics();
        this.maxWidth = maxWidth;
        this.x = x;
        this.y = y;

        //This is: the height above baseline, the height below baseline, and the font's recommended spacing between lines
        lineHeight = fontData.getAscent() + fontData.getDescent() + fontData.getLeading();

        this.generateTextLines(text, maxWidth);

        charactersWritten = 0;
        textLength = text.length();
        this.characterDelay = characterDelay;
    }

    /**
     * [setText]
     * sets the text of the TextDrawer, while retaining maximum width and x and y coordinates
     * @param text the new string for the TextDrawer to draw
     */
    public void setText(String text) {
        textLength = text.length();
        this.generateTextLines(text, maxWidth);
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
     * [speakText]
     * Draws text as if it were spoken, repeated method calls will start to draw more text over time.
     * @param g the graphics object to draw with
     */
    public void speakText(Graphics g) {
        //Tick timer
        if ((charactersWritten < textLength) && (System.currentTimeMillis() - lastTextUpdate > characterDelay)) {
            charactersWritten += 1;
            lastTextUpdate = System.currentTimeMillis();
        }
        int charactersSpoken = 0;
        int line = 0;

        while (charactersSpoken < charactersWritten) {
            //Draw either the entire line, or just the number of characters that should've been spoken so far
            int nextLineCharacters = Math.min(charactersWritten - charactersSpoken, lines[line].length());
            g.drawString(lines[line].substring(0, nextLineCharacters), x, y + lineHeight * line);
            charactersSpoken += lines[line].length();
            line++;
        }
    }

    /**
     * [generateTextLines]
     * Takes in an unstyled string and breaks it apart into lines
     * @param text The text to draw
     * @param maxWidth The maximum width (wrapping widt) of a line of text in pixels
     */
    private void generateTextLines(String text, int maxWidth) {
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

            //System.out.println(newLineIndex + " " + spaceIndex);

            //Create the word by breaking at the nearest space or newline
            if ((newLineIndex != -1) && (newLineIndex < spaceIndex)) {
                word = text.substring(0, newLineIndex + 1);
                text = text.substring(newLineIndex + 1);
            } else if (spaceIndex == -1) {
                word = text;
                text = "";
            } else {
                word = text.substring(0, spaceIndex + 1);
                text = text.substring(spaceIndex + 1);
            }

            //Get width of the word with the current graphics
            int wordWidth = fontData.stringWidth(word);

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

            if ((newLineIndex != -1) && (newLineIndex < spaceIndex)) {
                widthMarker = 0;
                tempLines.add(line);
                line = "";
            }
        }

        //Final incomplete line
        tempLines.add(line);

        //Create array of strings, one string for each line
        lines = new String[tempLines.size()];

        for (int i = 0; i < tempLines.size(); ++i) {
            lines[i] = tempLines.get(i);
        }
    }

    /**
     * [getLineHeight]
     * gets the height of a single line of text in pixels
     * @return int lineHeight, the computed height of a line of text based on ascent, descent, and leading
     */
    public int getLineHeight() {
        return lineHeight;
    }

    /**
     * [getTotalHeight]
     * @return the total height of the string in pixels, when converted into a set of lines
     */
    public int getTotalHeight() {
        return lineHeight * lines.length;
    }

    /**
     * [getCharactersWritten]
     * gets the number of characters written to the screen since last update
     * @return the total number of characters of the message written since last update
     */
    public int getCharactersWritten() {
        return charactersWritten;
    }

    /**
     * [setCharactersWritten]
     * sets the number of characters that should be displayed to the screen
     * @param charactersWritten the total number of characters that should be displayed to the screen
     * @return void
     */
    public void setCharactersWritten(int charactersWritten) {
        this.charactersWritten = charactersWritten;
    }

    /**
     * [getTextLength]
     * gets the length of the current text loaded into the text drawer
     * @return textLength the length of the current text loaded into the text drawer
     */
    public int getTextLength() {
        return textLength;
    }

}

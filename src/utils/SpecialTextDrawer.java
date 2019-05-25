package utils;

import java.awt.*;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;

public class SpecialTextDrawer {

    TextLayout[] lines;

    private void generateLines(Graphics g, String s, int maxWidth) {
        ArrayList<TextLayout> textLines = new ArrayList<>();

        //Create attributed string out of raw string
        AttributedString specialString = new AttributedString(s);

        //Create character iterator (which allows interaction with characters within)
        AttributedCharacterIterator charIter = specialString.getIterator();

        //Create line break measurer, which will automatically break lines
        LineBreakMeasurer lineMeasure = new LineBreakMeasurer(charIter, ((Graphics2D) g).getFontRenderContext());

        while (lineMeasure.getPosition() < charIter.getEndIndex()) {
            textLines.add(lineMeasure.nextLayout(maxWidth));
        }
    }
}

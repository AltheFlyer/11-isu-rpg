import utils.TextDrawer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [EnglishRoom.java]
 * Class for English room and its interactions
 * @version 1.1
 * @author Ethan Kwan
 * @since May 23, 2019
 */
public class EnglishRoom extends RoomMap {

     private int tileSize = 100;

     public EnglishRoom(GameIO fileManager, String mapPath, String walkabilityKey) {
         super(fileManager, mapPath,walkabilityKey);
     }

    /**
     * [event]
     * method is called whenever a new English room is created
     * runs a scripted event: Mr. Cimetta asks the class to hand in their tenth journal
     * @param g the graphics object used to draw with
     * @return void
     */
    @Override
    public void event(Graphics g) {
        //super.event(g);
        String message = "Alright, class. You've all done your 10th journal, right?\n...\n...What do you mean, no?";
        TextDrawer textDrawer = new TextDrawer(g,message,150,650,1166,50);
        g.setColor(Color.WHITE);
        g.fillRect(100,600,1165,100); //fill message box
        g.fillRect(100,550,150,50); //fill name box
        g.setColor(Color.BLACK);
        g.drawRect(100,600,1165,100); //outline message box
        g.drawRect(100,550,150,50); //outline name box
        g.drawString("Mr. Cimetta", 120,580); //draw name
        textDrawer.speakText(g); //draw message
        if (textDrawer.getCharactersWritten() == message.length()) {
            super.event(g);
        }
        System.out.println("works");
     }

}

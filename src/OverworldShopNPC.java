import utils.TextDrawer;

import java.awt.Graphics;
import java.awt.Color;

/**
 * [OverworldShopNPC.java]
 * Class for NPCs that can open a shop interface on any overworld map
 * @version 1.1
 * @author Jasmine Chu
 * @since June 10, 2019
 */
public class OverworldShopNPC extends OverworldNPC {

    private Item[] items;

    public OverworldShopNPC(int x, int y, String name, String message, Item[] items) {
        super(x, y, name, message);
        this.items = items;
    }

    /**
     * [speak]
     * draws the NPC's text box and speech
     * @param g the graphics object to draw with
     */
    @Override
    public void speak(Graphics g, TextDrawer textDrawer) {
        textDrawer.setText(this.getMessage());
        g.setColor(Color.WHITE);
        g.fillRect(750,150,515,450); //fill shop box
        g.fillRect(100,600,1165,100); //fill message box
        g.fillRect(100,550,150,50); //fill name box
        g.setColor(Color.BLACK);
        g.drawRect(750,150,515,450); //outline shop box
        g.drawRect(100,600,1165,100); //outline message box
        g.drawRect(100,550,150,50); //outline name box
        for (int i = 0; i < items.length; ++i) {
            g.drawString(items[i].getName(), 850, 200 + 50 * i); //draw name of current item
            g.drawString(Integer.toString(items[i].getCost()), 1050, 200 + 50 * i); //draw cost of current item
        }
        g.drawString(this.getName(),120,580); //draw name
        textDrawer.drawText(g); //draw message
        g.drawString("Press z to continue..",1111,675); //draw continue message
    }

    /**
     * [shopIsOpen]
     * returns if the NPC's shop interface is open or not
     * @return boolean isTalking, if the NPC is talking or not
     */
    @Override
    public boolean shopIsOpen() {
        return this.isTalking();
    }

    /**
     * [getItems]
     * gets the NPC's array of items
     * @return items, the array of the NPC's items for sale
     */
    public Item[] getItems() {
        return this.items;
    }

}

import java.awt.*;

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
     * @return void
     */
    public void speak(Graphics g) {
        utils.TextDrawer textDrawer = new utils.TextDrawer(g,this.getMessage(),150,650,1166,50);
        g.setColor(Color.WHITE);
        g.fillRect(750,150,515,450); //fill shop box
        g.fillRect(100,600,1165,100); //fill message box
        g.fillRect(100,550,150,50); //fill name box
        g.setColor(Color.BLACK);
        g.drawRect(750,150,515,450); //outline shop box
        g.drawRect(100,600,1165,100); //outline message box
        g.drawRect(100,550,150,50); //outline name box
        for (int i = 0; i < items.length; ++i) {
            g.drawString(items[i].getName(), 850, 250 + 100 * i); //draw name of current item
            g.drawString(Integer.toString(items[i].getCost()), 1050, 250 + 100 * i); //draw cost of current item
        }
        g.drawString(this.getName(),120,580); //draw name
        textDrawer.drawText(g); //draw message
        g.drawString("Press z to continue..",1111,675); //draw continue message
    }

    @Override
    /**
     * [shopIsOpen]
     * returns if the NPC's shop interface is open or not
     * @return boolean isTalking, if the NPC is talking or not
     */
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

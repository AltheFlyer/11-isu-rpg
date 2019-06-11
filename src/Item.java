import java.awt.Rectangle;

/**
 * [Item.java]
 * Class for items that can be bought from OverworldShopNPCs
 * @version 1.1
 * @author Jasmine Chu
 * @since June 10, 2019
 */
public class Item {

    static int width = 200;
    static int height = 100;
    private String name;
    private int cost;
    private Rectangle boundingBox;

    public Item(String name, int cost, int x, int y) {
        this.name = name;
        this.cost = cost;
        this.boundingBox = new Rectangle(x, y, height, width);
    }

    /**
     * [getName]
     * returns the name of the item
     * @return String name, the name of the item
     */
    public String getName() { return this.name; }

    /**
     * [getCost]
     * returns the cost of the item
     * @return int cost, the cost of the item
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * [getBoundingBox]
     * gets the items's bounding box for mouse interaction
     * @return Rectangle, the item's bounding box for interaction
     */
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

}

/**
 * [Item.java]
 * Class for items that can be bought from OverworldShopNPCs
 * @version 1.1
 * @author Jasmine Chu
 * @since June 10, 2019
 */
public class Item {

    private String name;
    private int cost;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
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

}

import java.awt.*;

public class OverworldNPC extends OverworldEntity {

    public OverworldNPC(int x, int y) {
        super(x,y);
    }

    public void draw(Graphics g, OverworldMap map, OverworldPlayer player) {
        g.setColor(Color.BLUE);
        if (map instanceof RoomMap) { //regular drawing
            g.fillRect(this.getX(), this.getY(), 50, 50); //modify size
        } else { //draw NPC in relation to player location in map and moving map thing
            int xDifference = player.getX() - this.getX();
            int yDifference = player.getY() - this.getY();
            int xLocation = 683 - 50 - xDifference;
            int yLocation = 384 - 50 - yDifference;
            g.fillRect(xLocation, yLocation, 50, 50);
        }
    }

}

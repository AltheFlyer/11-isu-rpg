import java.awt.*;

abstract public class MapObject {

    private Rectangle boundingBox;
    private int x;
    private int y;

    public MapObject(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        boundingBox = new Rectangle(this.x, this.y, height, width);
    }

}

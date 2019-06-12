import java.awt.Rectangle;

/**
 * [Mirror.java]
 * Class for mirror object that reflects a ball on the map
 * @version 1.1
 * @author Ethan Kwan
 * @since June 11, 2019
 */

public class Mirror extends OverworldObject {

    public Mirror(int x, int y) {
        super(x, y);
        this.setXVelocity(0);
        this.setYVelocity(0);
    }

}

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * [Intent.java]
 * @version 0.1a
 * @author Allen Liu
 * @since TODO
 * A class that represents an intent of an enemy, and how the user can interact with it
 */
public class Intent extends StatusIcon {

    Enemy enemy;

    public Intent(Enemy e, BufferedImage icon, String desc, int x, int y, int width, int height) {
        super(icon, desc, x, y, width, height);
        this.enemy = e;
    }


}

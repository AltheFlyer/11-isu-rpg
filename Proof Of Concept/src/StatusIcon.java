import java.awt.*;
import java.awt.image.BufferedImage;

public class StatusIcon implements Hoverable{

    private String context;
    private BufferedImage sprite;
    private Rectangle hitbox;

    public StatusIcon(BufferedImage icon, String desc, int x, int y) {
        this.sprite = icon;
        this.context = desc;
        this.hitbox = new Rectangle(x, y, icon.getWidth(), icon.getHeight());
    }

    public StatusIcon(BufferedImage icon, String desc, int x, int y, int width, int height) {
        this.sprite = icon;
        this.context = desc;
        this.hitbox = new Rectangle(x, y, width, height);
    }

    @Override
    public boolean contains(int x, int y) {
        return hitbox.contains(x, y);
    }

    public void draw(Graphics g) {

        g.drawImage(sprite, hitbox.x, hitbox.y, hitbox.width, hitbox.height,null);
    }
}

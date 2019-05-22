import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [Tile.java]
 * @version 0.1a
 * @author Allen Liu
 * @since TODO
 * Represents a tile which can have a character on it
 */
abstract public class Tile {

    private BufferedImage sprite;
    private Entity entity;
    private Rectangle boundingBox;

    public Tile() {
        try {
            sprite = ImageIO.read(new File("src/tile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBoundingBox(Rectangle r) {
        boundingBox = r;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height, null);
    }

    public void drawSpecial(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setSprite(BufferedImage image) {
        this.sprite = image;
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }
}

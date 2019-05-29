import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [Icon.java]
 * represents icons in battle (status, intent)
 * @version 1.0
 * @author Allen Liu
 * @since May 27, 2019
 */
public class Icon {

    private Rectangle boundingBox;
    private BufferedImage sprite;
    //Should probably be based on the entity source
    private int centerX, centerY;
    //Use for the image values
    private int graphicsWidth, graphicsHeight;

    private String name;
    private String description;

    /**
     * Creates an icon with a bounding box and sprite
     * @param boundingBox the icon's bounding box for mouse interactions
     * @param path the full path to use for the sprite
     */
    public Icon(Rectangle boundingBox, String path) {
        try {
            this.sprite = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            this.sprite = new BufferedImage(0, 0, 0);
        }
        this.boundingBox = boundingBox;

        this.centerX = boundingBox.x;
        this.centerY = boundingBox.y;
        this.graphicsWidth = boundingBox.width;
        this.graphicsHeight = boundingBox.height;

        this.name = "";
        this.description = "";
    }

    /**
     * Creates an icon with a bounding box and sprite
     * @param boundingBox the icon's bounding box for mouse interactions
     * @param sprite the sprite to use for the icon
     */
    public Icon(Rectangle boundingBox, BufferedImage sprite) {
        this.boundingBox = boundingBox;
        this.sprite = sprite;

        this.centerX = boundingBox.x;
        this.centerY = boundingBox.y;
        this.graphicsWidth = boundingBox.width;
        this.graphicsHeight = boundingBox.height;

        this.name = "";
        this.description = "";
    }

    /**
     * [draw]
     * draws the icon
     * @param g the graphics object to draw with
     */
    public void draw(Graphics g) {
        //Draw sprite centered at specified point
        g.drawImage(sprite, centerX - (graphicsWidth / 2), centerY - (graphicsHeight / 2), null);
    }

    /**
     * [getName]
     * gets the icon's name
     * @return String, the name of the icon
     */
    public String getName() {
        return name;
    }

    /**
     * [setName]
     * sets the icon's name
     * @param name the name for the icon
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * [getDescription]
     * gets the icon's description
     * @return String, the description of the icon
     */
    public String getDescription() {
        return description;
    }

    /**
     * [setDescription]
     * sets the icon's description (for more in depth text)
     * @param description a longer description of the icon
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * [getBoundingBox]
     * gets the icon's bounding box for mouse interaction
     * @return Rectangle, the icon's bounding box for interaction
     */
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    /**
     * [setBoundingBox]
     * sets the icon's bounding box for interaction
     * @param boundingBox the icon's new bounding box
     */
    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * [setSprite]
     * sets the icon's sprite
     * @param sprite the sprite to display for this icon
     */
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * [setCenterX]
     * sets the icon center's x coordinate
     * @param centerX the icon center's x coordinate
     */
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    /**
     * [setCenterY]
     * sets the icon center's y coordinate
     * @param centerY the icon center's y coordinate
     */
    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    /**
     * [setGraphicsWidth]
     * sets the width of the sprite
     * @param graphicsWidth the width of the drawn sprite
     */
    public void setGraphicsWidth(int graphicsWidth) {
        this.graphicsWidth = graphicsWidth;
    }

    /**
     * [setGraphicsHeight]
     * sets the height of the sprite
     * @param graphicsHeight the height of the drawn sprite
     */
    public void setGraphicsHeight(int graphicsHeight) {
        this.graphicsHeight = graphicsHeight;
    }

    /**
     * [setPosition]
     * sets the graphical and bounding box positions (by center)
     * @param xCenter the x value of the new center
     * @param yCenter the y value of the new center
     */
    public void setPosition(int xCenter, int yCenter) {
        centerX = xCenter;
        centerY = yCenter;

        boundingBox.setLocation(xCenter - (boundingBox.width / 2), yCenter - (boundingBox.height / 2));
    }
}

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy extends Entity {

    Intent intention;

    BufferedImage hyperBeam;
    BufferedImage creativeAI;

    Enemy(String name, double hp, double en, double atk, double def) {
        super(name, hp, en, atk, def);
        try {
            this.setSprite(ImageIO.read(new File("src/Untitled.png")));
            hyperBeam = ImageIO.read(new File("src/intent_hyper_beam.png"));
            creativeAI = ImageIO.read(new File("src/intent_creative_ai.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setHitbox(new Rectangle(600, 200, 80, 80));
        act();
    }

    public void act() {
        if (Math.random() < 0.5) {
            intention = new Intent(this, hyperBeam, "The most powerful ability ever", getCenterX() - 20, getY() - 80, 40, 40);
        } else {
            intention = new Intent(this, creativeAI, "The most powerful ability ever", getCenterX() - 20, getY() - 80, 40, 40);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(getSprite(), getX(), getY(), null);
        intention.draw(g);
    }

}

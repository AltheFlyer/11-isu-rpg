import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    private double health;
    private double energy;
    private double attack;
    private double defense;

    private double maxHealth;
    private double maxEnergy;
    private double baseAttack;
    private double baseDefense;

    private String name;

    private BufferedImage sprite;

    private Rectangle hitbox;

    Entity(String name, double hp, double en, double atk, double def) {
        this.name = name;
        maxHealth = hp;
        maxEnergy = en;
        baseAttack = atk;
        baseDefense = def;
        health = maxHealth;
        energy = maxEnergy;
        attack = baseAttack;
        defense = baseDefense;
        hitbox = new Rectangle(0, 0, 0, 0);
    }

    public void setX(int x) {
        hitbox.x = x;
    }

    public void setY(int y) {
        hitbox.y = y;
    }

    public int getX() {
        return hitbox.x;
    }

    public int getY() {
        return hitbox.y;
    }

    public int getCenterX() {
        return hitbox.x + (hitbox.width / 2);
    }

    public int getCenterY() {
        return hitbox.y + (hitbox.height / 2);
    }
    public void setHitbox(Rectangle r) {
        hitbox = r;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setSprite(BufferedImage spr) {
        this.sprite = spr;
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }

    public void draw(Graphics g) {
        g.drawImage(getSprite(), getX(), getY(), null);
    }
}

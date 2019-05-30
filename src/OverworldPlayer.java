import java.awt.*;

public class OverworldPlayer extends OverworldEntity {

    public OverworldPlayer(int x, int y){
        super(x, y);
    }

    public void draw(Graphics g, OverworldMap map) {
        g.setColor(Color.RED);
        if (map instanceof RoomMap) {
            g.fillRect(this.getX(), this.getY(), 50, 50); //modify size
        } else {
            g.fillRect(683 - 50, 384 - 50, 50, 50); //modify size
        }
    }

    public void moveUp() {
        this.setY(this.getY() - 5);
        this.setBoundingBox(this.getX(),this.getY());
    }

    public void moveDown() {
        this.setY(this.getY() + 5);
        this.setBoundingBox(this.getX(),this.getY());
    }

    public void moveLeft() {
        this.setX(this.getX() - 5);
        this.setBoundingBox(this.getX(),this.getY());
    }

    public void moveRight() {
        this.setX(this.getX() + 5);
        this.setBoundingBox(this.getX(),this.getY());
    }

    public void interact() {
        Rectangle interactChecker;
        if (this.getDirection().equals("down")) {
            interactChecker = new Rectangle(this.getX(), this.getY() + this.getSize(), this.getSize(), this.getSize());
        } else if (this.getDirection().equals("up")) {
            interactChecker = new Rectangle(this.getX(), this.getY() - this.getSize(), this.getSize(), this.getSize());
        } else if (this.getDirection().equals("right")) {
            interactChecker = new Rectangle(this.getX() + this.getSize(), this.getY(), this.getSize(), this.getSize());
        } else if (this.getDirection().equals("left")) {
            interactChecker = new Rectangle(this.getX(), this.getY() - this.getSize(), this.getSize(), this.getSize());
        }
    }

}

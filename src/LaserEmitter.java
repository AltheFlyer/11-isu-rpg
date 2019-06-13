import java.awt.*;

/**
 * [LaserEmitter.java]
 * Class for the laser emitter object
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class LaserEmitter extends OverworldObject {

    private int angle = 90;
    private int sliderX = 678;
    private Ball ball;

    public LaserEmitter(int x, int y) {
        super(x, y);
        this.setXVelocity(0);
        this.setYVelocity(0);
    }

    /**
     * [checkInteractions]
     * checks if player's interaction hitbox is intersecting with this laser emitter
     * if so, open angle modification interface
     * @param hitbox, the player's interaction hitbox
     * @return void
     */
    @Override
    public void checkInteractions(Rectangle hitbox) {
        if (hitbox.intersects(this.collisionWindow())) {
            this.toggleInterface();
            if (!this.isInterfaceOpen()) {
                ball = new Ball(900, 150, getBallXVelocity(degreesToRad(angle)), getBallYVelocity(degreesToRad(angle)));
            }
        }
    }

    /**
     * [move]
     * if spawned, moves the ball
     * @param elapsedTime elapsed time between last time check and current time in seconds
     * @return void
     */
    @Override
    public void move(double elapsedTime) {
        if (ball != null) {
            ball.move(elapsedTime);
        }
    }

    /**
     * [draw]
     * draws this object and also the ball that it spawns
     * @param g the graphics object to draw with
     * @param map the OverworldMap the object is inhabiting
     * @param player the player inhabiting the same map
     * @return void
     */
    @Override
    public void draw(Graphics g, OverworldMap map, OverworldPlayer player) {
        g.setColor(Color.BLUE);
        int xDifference = player.getX() - this.getX();
        int yDifference = player.getY() - this.getY();
        int xLocation = 683 - xDifference;
        int yLocation = 384 - yDifference;
        g.fillRect(xLocation, yLocation, 50, 50);
        if (ball != null) {
            if (ball.getBounces() > 10) {
                ball = null;
            } else {
                ball.checkCollisions(map.getMap()[ball.getX()/map.getTileSize()][ball.getY()/map.getTileSize()]);
                ball.draw(g, player);
            }
        }
    }

    /**
     * [openInterface]
     * draws this object's interactable interface
     * a slider that determines what angle to launch the ball at
     * @param g the graphics object to draw with
     * @return void
     */
    @Override
    public void openInterface(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(483,304,400,200);
        g.setColor(Color.BLACK);
        g.drawRect(483,304,400,200);
        g.drawString("Move the slider with your arrow keys to adjust the angle of firing.", 520, 370);
        g.drawLine(593,404,773,404);
        g.setColor(Color.WHITE);
        g.fillRect(sliderX,379,10,50);
        g.setColor(Color.BLACK);
        g.drawRect(sliderX,379,10,50);
        g.drawString("Angle: " + angle + " degrees",560,480);
    }

    /**
     * [moveSliderRight]
     * moves this object's interactable slider towards the right
     * @return void
     */
    public void moveSliderRight() {
        if (sliderX < 768) {
            this.sliderX = this.sliderX + 1;
            this.angle = this.angle + 1;
        }
    }

    /**
     * [moveSliderLeft]
     * moves this object's interactable slider towards the left
     * @return void
     */
    public void moveSliderLeft() {
        if (sliderX > 588) {
            this.sliderX = this.sliderX - 1;
            this.angle = this.angle - 1;
        }
    }

    /**
     * [setXVelocity]
     * sets the ball's x velocity according to an angle on a unit circle in radians
     * multiplied by a factor of 5 for speed
     * @param angle the angle that denotes what direction the ball goes in
     * @return the new x velocity of the ball
     */
    public double getBallXVelocity(double angle) {
        return Math.cos(angle)*5;
    }

    /**
     * [setYVelocity]
     * sets the ball's y velocity according to an angle on a unit circle in radians
     * mulitiplied by a factor of 5 for speed
     * @param angle the angle that denotes what direction the ball goes in
     * @return the new y velocity of the ball
     */
    public double getBallYVelocity(double angle) {
        return Math.sin(angle)*5;
    }

    /**
     * [degreesToRad]
     * converts an angle from degrees to radians
     * note: due to the nature of the positioning of the object, the angle is shifted one quadrant counter-clockwise
     * @param angle the angle in degrees to convert to radians
     * @return the same angle, now in radians, shifted one quadrant counter-clockwise
     */
    private double degreesToRad(int angle) {
        return angle*(Math.PI/180) + Math.PI/2;
    }

}

import java.awt.*;

/**
 * [LaserEmitter]
 * Class for the laser emitter object
 * @version 1.1
 * @author Ethan Kwan
 * @since June 12, 2019
 */
public class LaserEmitter extends OverworldObject {

    private int angle = 90;
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
            ball = new Ball(1100, 200, 10, 10);
        }
    }

    @Override
    public void draw(Graphics g, OverworldMap map, OverworldPlayer player) {
        super.draw(g, map, player);
        if (ball != null) {
            if (ball.getBounces() > 10) {
                ball = null;
            } else {
                ball.checkCollisions(map.getMap()[ball.getX()/map.getTileSize()][ball.getY()/map.getTileSize()]);
                ball.move();
                ball.draw(g, player);
            }
        }
    }
}

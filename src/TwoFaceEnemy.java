import utils.AnimatedSprite;

import java.awt.*;

public class TwoFaceEnemy extends Enemy {

    AnimatedSprite happyFace;
    AnimatedSprite sadFace;

    /**
     * Creates an enemy entity which doesn't need energy
     * @param x         the x Location on the grid that the enemy is located on
     * @param y         the y location on the grid that the enemy is located
     */
    TwoFaceEnemy(int x, int y) {
        super(x, y, 500, 30, 0, "Two Face", null,
                new Ability[] {

                });

        happyFace = new AnimatedSprite("spritesheets/happy_face.png", 1, 7, 250);
        sadFace = new AnimatedSprite("spritesheets/happy_face.png", 1, 7, 250);
    }

    /**
     * [decide]
     * generates a next move (Ability and intent) - that the player can then react to (should be overriden).
     *
     * @param map the map that the entities are in
     */
    @Override
    public void decide(JointMap map) {

    }

    /**
     * [act]
     * lets the enemy perform an action using an ability
     *
     * @param map the map that the entities are in
     */
    @Override
    public void act(JointMap map) {

    }

    @Override
    public void draw(int x, int y, Graphics g, boolean indicated) {

        g.setColor(Color.ORANGE);
        g.fillRect(x,y,120,120);

        happyFace.draw(g, x, y);

        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
    }
}

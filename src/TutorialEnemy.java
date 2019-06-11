import utils.AnimatedSprite;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [TutorialEnemy.java]
 * A basic enemy that alternates between attacking and moving
 * @version 1.0
 * @author Allen Liu
 * @since May 31, 2019
 */
public class TutorialEnemy extends Enemy {

    Icon attackIcon;
    Icon uselessIcon;
    int turn;

    BufferedImage sprite;

    Ability attackAbility;
    Ability seek;
    Ability uselessAbility;

    /**
     * [TutorialEnemy]
     * creates a tutorial enemy with a set position
     * @param x the x tile to start on
     * @param y the y tile to start on
     */
    TutorialEnemy(int x, int y) {
        super(x, y, 100, 35, 0, "Annoying Peon",
                new AnimatedSprite("sprites/slime.png", 1, 1, 100000),
                new Ability[] {
                        new SingleAbility(new AnimatedSprite("spritesheets/basicAttack.png", 1,5,250),"Basic Attack", "Deals damage to a single target in the same row, then moves to the same row as a player.",
                        0, 2, 6, 0, 0, 1, true, false
                        ),
                        new BasicMoveAbility("Rest", "This ability is useless!",
                                0, 2,0)
                });

        attackIcon = new Icon("assets/icons/sword.png");
        attackIcon.setName("Basic Attack");
        uselessIcon = new Icon("assets/icons/move.png");
        uselessIcon.setName("Useless!");
        uselessIcon.setDescription("The enemy intends to do... nothing!");
        turn = 0;

        attackAbility = getAbility(0);
        uselessAbility = getAbility(1);
        seek = new BasicMoveAbility("", "", 0, 0, 2);

        try {
            sprite = ImageIO.read(new File("assets/sprites/slime.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * [decide]
     * Chooses actions for the enemy. It will always choose to move for odd turns,
     * and will always choose to attack for even turns
     * @param map the JointMap that the enemy is on
     */
    @Override
    public void decide(JointMap map) {
        //Attacks on even turns, moves on odd turns
        if (turn % 2 == 1) {
            setIntent(attackIcon);
            setDecide(attackAbility);
        } else {
            setIntent(uselessIcon);
            setDecide(uselessAbility);
        }
        this.turn++;
    }

    /**
     * [act]
     * tutorial enemy actions, it will always move on odd turns, and will always attack on even turns
     * @param map the JointMap that the enemy is on
     */
    @Override
    public void act(JointMap map) {
        if (turn % 2 == 0) {
            selectRandomTile(map, attackAbility);
            selectRowWithMostPlayers(map, seek);
        }
        //Useless ability is useless!
    }

    /**
     * [draw]
     * draws the enemy's sprite
     * @param x the x coordinate to draw at
     * @param y the y coordinate to draw at
     * @param g the graphics object to draw with
     * @param indicated whether the enemy is indicated or not
     */
    @Override
    public void draw(int x, int y, Graphics g, boolean indicated) {
        super.draw(x, y, g, indicated);
        g.drawImage(sprite, x, y, null);
    }

    /**
     * [drawAbilities]
     * draws the enemy's abilities and profile
     * @param g the graphics object to draw with
     */
    @Override
    public void drawAbilities(Graphics g) {
        super.drawAbilities(g);
        g.drawImage(sprite, 1069, 15+105*abilities.length, 60, 60,null);
    }

}

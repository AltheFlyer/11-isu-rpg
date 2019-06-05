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
    Icon moveIcon;
    int turn;

    BufferedImage sprite;

    Ability attackAbility;
    Ability moveAbility;

    TutorialEnemy(int x, int y) {
        super(10, "Annoying Peon",
                new Ability[] {
                        new SingleAbility("Basic Attack", "Deals damage to a single target in the same row.",
                        0, 2, 6, 0, 1, 8, true, false
                        ),
                        new BasicMoveAbility("Seek", "Moves to the same row as the player.",
                                0, 2,2)
                });

        attackIcon = new Icon("assets/icons/sword.png");
        attackIcon.setName("Basic Attack");
        moveIcon = new Icon("assets/icons/move.png");
        moveIcon.setName("Seek!");
        turn = 1;

        attackAbility = getAbility(0);
        moveAbility = getAbility(1);

        setIntent(moveIcon);
        setDecide(moveAbility);

        setXGrid(x);
        setYGrid(y);

        try {
            sprite = ImageIO.read(new File("assets/sprites/slime.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decide(JointMap map) {
        //Attacks on even turns, moves on odd turns
        System.out.println(turn);
        if (turn % 2 == 1) {
            setIntent(attackIcon);
            setDecide(attackAbility);
        } else {
            setIntent(moveIcon);
            setDecide(moveAbility);
        }
        this.turn++;
    }

    @Override
    public void act(JointMap map) {
        //The abilities used are swapped from the abilities decided
        if (turn % 2 == 1) {
            //move to row with most players
            int optimalRow = 0;
            int maxRowPlayers = 0;

            for (int y = 0; y < 3; ++y) {
                int playerCount = 0;
                for (int x = 0; x < 3; ++x) {
                    if (!map.isEmpty(x, y)) {
                        playerCount++;
                        if (playerCount > maxRowPlayers) {
                            optimalRow = y;
                            maxRowPlayers = playerCount;
                        }
                    }
                }
            }

            getDecide().indicateValidTiles(map);
            getDecide().action(map, getXGrid(), optimalRow);
        } else {
            selectRandomTile(map, getDecide());
        }
    }

    @Override
    public void draw(int x, int y, Graphics g, boolean indicated) {
        super.draw(x, y, g, indicated);
        g.drawImage(sprite, x, y, null);
    }

    @Override
    public void drawAbilities(Graphics g) {
        super.drawAbilities(g);
        g.drawImage(sprite, 1069, 15+105*abilities.length, 60, 60,null);
    }

}

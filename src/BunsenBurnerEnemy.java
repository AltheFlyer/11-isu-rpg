import utils.AnimatedSprite;

import java.awt.Graphics;

/**
 * [BunsenBurnerEnemy.java]
 * An enemy that burns players.
 * @version 1.0
 * @author Allen Liu
 * @since June 6, 2019
 */
public class BunsenBurnerEnemy extends Enemy {

    Icon attackIcon;

    BunsenBurnerEnemy(int x, int y) {
        super(x, y, 200, 25, 0,"Bunsen Burner",
                new AnimatedSprite("spritesheets/bunsen.png", 1, 10, 100),
                new Ability[] {
                    new AOEAbility(new AnimatedSprite("spritesheets/inflame.png", 1, 2, 600),"Inflame", "Burns all targets in the row.", 0, 1,
                            6, 0, 6, 0, 0,1, true, false)
        });

        attackIcon = new Icon("assets/icons/sword.png", "Inflame", "This enemy intends to attack all players in the row.");
    }

    @Override
    public void decide(JointMap map) {
        setIntent(attackIcon);
        setDecide(abilities[0]);
    }

    @Override
    public void act(JointMap map) {
        getDecide().indicateValidTiles(map);
        getDecide().action(map, 2, getYGrid());

        setTargetedX(2);
        setTargetedY(getYGrid());
    }

    @Override
    public void draw(int x, int y, Graphics g, boolean indicated) {
        super.draw(x, y, g, indicated);
    }
}

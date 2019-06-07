import utils.AnimatedSprite;

import java.awt.*;

/**
 * [BunsenBurnerEnemy.java]
 * An enemy that burns players.
 * @version 1.0
 * @author Allen Liu
 * @since June 6, 2019
 */
public class BunsenBurnerEnemy extends Enemy {

    Icon attackIcon;

    AnimatedSprite gif;

    BunsenBurnerEnemy(int x, int y) {
        super(35, "Bunsen Burner", new Ability[] {
                new AOEAbility(null,"Inflame", "Burns all targets in the row.", 0, 1,
                        6, 0, 6, 0, 1, 5, true, false)
        });

        attackIcon = new Icon("assets/icons/sword.png", "Inflame", "This enemy intends to attack all players in the row.");

        setIntent(attackIcon);
        setDecide(abilities[0]);

        setXGrid(x);
        setYGrid(y);

        gif = new AnimatedSprite("spritesheets/bunsen.png", 1, 10, 100);
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
    }

    @Override
    public void draw(int x, int y, Graphics g, boolean indicated) {
        super.draw(x, y, g, indicated);
        gif.draw(g, x, y);
    }
}

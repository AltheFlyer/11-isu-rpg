import utils.AnimatedSprite;
import utils.TextDrawer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 * [TutorialLevel.java]
 * The tutorial level for the game, containing a simplified level with a preset player, enemy, and a set of dynamic
 * instructions based on player actions.
 * @version 1.1
 * @author Allen Liu
 * @since June 7, 2019
 */
public class TutorialLevel extends LevelScreen {

    private TextDrawer tutorialPrompt;
    private Rectangle textBack;

    private int tutorialIndex;
    private String[] tutorial;

    private int tutorialX, tutorialY;

    /**
     * [TutorialLevel]
     * creates a tutorial screen within the running game
     * @param game the currently running game
     */
    TutorialLevel(GameManager game) {
        super(game, new Enemy[] {new TutorialEnemy(3, 1)});

        jointMap = new JointMap();

        Enemy e = new TutorialEnemy(3, 1);
        jointMap.addEntity(3, 1, e);
        enemies = new Enemy[1];
        enemies[0] = e;
        jointMap.generateEnemyDecisions(e);

        BasicMoveAbility moveAbility = new BasicMoveAbility("Move", "Move to an adjacent tile.",30, 2,  1);
        //Tune the move ability so it can't be used on turn 1
        moveAbility.resetCooldown();
        moveAbility.lowerCooldown(1);

        players = new Player[1];
        players[0] =  new Player(100, 35, 0, 30, "tutorial", "Allen",
                new AnimatedSprite("spritesheets/Jasmine.png", 1, 5, 100),
                new Ability[] {
                        new SingleAbility(new AnimatedSprite("spritesheets/basicAttack.png", 1, 5, 250),
                                "Basic Attack", "Deals damage to a single target.",
                                30, 1, 6, 0, 0, 1, true, false),
                        moveAbility
                });

        //Tutorial should be simplified: 1 player character with only 2 abilities.
        jointMap.addEntity(2, 1, players[0]);

        tutorialX = 1 + 323 + 121 * 3;
        tutorialY = 1 + 108 + 121 * 3;

        textBack = new Rectangle(tutorialX, tutorialY, 121 * 3, 160);

        tutorial = new String[] {
                "Select the player using the menu bar to the left or by clicking on the player in the grid.",

                "With a character selected, you can see their abilities to the left. Hover over them to see their range. " +
                        "You can deselect them by clicking on an empty tile.",

                "Select an ability by clicking on it. You can then hover over the grid to see which tiles can be targeted." +
                        "Try selecting the basic attack.",

                "You can use the ability by clicking on a valid tile. If you click on a tile that cannot be targeted, it will deselect the ability. " +
                        "Try to hit the enemy on the right. You'll need to be in the same row as it to do so.",

                "Be aware of ability cooldowns and energy cost - you won't always be able to use your most powerful attacks." +
                        "In this battle, plan your movements wisely to avoid damage.",

                "Note that you can also see enemy information by clicking on them without a selected ability. " +
                        "You can also see their intents by hovering over the icon above them." +
                        "Try to plan your moves based on enemy intents - you could avoid damage and hit at opportune moments.",

                "Press the End turn button when your turn is over.",

                "You should perform an action before ending your turn!\n\n" +
                        "Start by selecting the player using the menu bar to the left or by clicking on the player in the grid.",

                "The enemy intends to attack now! You should move out of the way. Use the move ability to move to another column." +
                        "This will let you avoid attacks. Use it wisely!"
        };
    }

    /**
     * [paintComponent]
     * draws the battle screen along with additional tutorial prompt boxes
     * @param g the graphics object to draw with
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!(arePlayersDead() || areEnemiesDead())) {
            //Draw the text drawer, if it is null, set it using the graphics object
            if (tutorialPrompt != null) {
                g.setColor(Color.WHITE);
                g.fillRect(textBack.x, textBack.y, textBack.width, textBack.height);

                g.setColor(Color.BLACK);
                tutorialPrompt.drawText(g);
            } else {
                tutorialPrompt = new TextDrawer(g, tutorial[0], tutorialX + 10, tutorialY + 20, 121 * 3 - 20);
            }

            //Don't display default messages if the player attempts to end the turn early
            if (tutorialIndex != 7) {
                //Dynamic tutorial, try to draw the appropriate text based on current character action.
                if (turnNumber == 2) {
                    tutorialIndex = 8;
                } else if (players[0].getEnergy() == 0) {
                    if (selectedEnemy != null) {
                        tutorialIndex = 6;
                    } else {
                        tutorialIndex = 5;
                    }
                } else if (selectedAbility != null) {
                    //Different advice for the move ability
                    if (selectedAbility.getCooldown() == 2) {
                        tutorialIndex = 4;
                    } else {
                        tutorialIndex = 3;
                    }
                } else if (selectedPlayer != null) {
                    if (getMouseX() < 323) {
                        tutorialIndex = 2;
                    } else {
                        tutorialIndex = 1;
                    }
                } else {
                    tutorialIndex = 0;
                }
            }

            tutorialPrompt.setText(tutorial[tutorialIndex]);
        }

        repaint();
    }

    /**
     * [mousePressed]
     * resets the 'end turn' hint of the tutorial
     * @param e the mouse event that is sent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        //quick tutorial reset
        if (tutorialIndex == 7) {
            tutorialIndex = 0;
        }
    }

    /**
     * [endTurn]
     * runs end of turn actions, but prevents the first turns from ending without performing any actions
     */
    @Override
    public void endTurn() {
        if ((players[0].getEnergy() == 0) || (turnNumber >= 2)) {
            super.endTurn();
        } else {
            tutorialIndex = 7;
            tutorialPrompt.setText(tutorial[tutorialIndex]);
        }
    }

    /**
     * [loseBattle]
     * handles loss condition (if all players die) and give the player the option of restarting the encounter
     * or returning to map
     * @param g the graphics object to draw with
     */
    @Override
    public void loseBattle(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(100, 100, 1366 - 200, 768 - 200);

        g.setColor(Color.WHITE);

        g.drawString("You lost...", 200, 200);

        g.fillRect(exitLevelButton.x, exitLevelButton.y, exitLevelButton.width, exitLevelButton.height);
        g.setColor(Color.BLACK);
        g.drawString("Retry?", exitLevelButton.x + 10, exitLevelButton.y + 20);
    }

    /**
     * [endBattleLoader]
     * loads the map for the end of battle, progressing time state if the battle was won
     */
    public void endBattleLoader() {
        if (!arePlayersDead()) {
            getIO().setTimeState(getIO().getCurrentPeriod() + 1, getIO().getCurrentDay());
            getIO().writeTimeState();
            setScreen(new LoadingScreen(getGame()));
            enterMapScreen();
        } else {
            setScreen(new TutorialLevel(getGame()));
        }
    }
}

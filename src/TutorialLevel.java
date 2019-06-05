import utils.TextDrawer;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TutorialLevel extends LevelScreen {

    TextDrawer tutorialPrompt;
    Rectangle textBack;

    int tutorialIndex;
    String[] tutorial;

    int tutorialX, tutorialY;

    TutorialLevel(GameManager game) {
        super(game);

        jointMap = new JointMap();

        BasicMoveAbility moveAbility = new BasicMoveAbility("Move", "Move to an adjacent tile.",30, 2,  1);
        //Tune the move ability so it can't be used on turn 1
        moveAbility.resetCooldown();
        moveAbility.lowerCooldown(1);

        players = new Player[1];
        players[0] =  new Player(10, 30, "Allen",
                new Ability[] {
                        new SingleAbility("Basic Attack", "Deals damage to a single target.",
                                30, 1, 6, 0, 1, 3, true, false),
                        moveAbility
                });

        //enemies
        enemies = new Enemy[9];
        enemies[0] = new TutorialEnemy();

        //Tutorial should be simplified: 1 player character with only 2 abilities.
        jointMap.addEntity(2, 1, players[0]);
        jointMap.addEntity(3, 1, enemies[0]);

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
                        "Start by selecting the player using the menu bar to the left or by clicking on the player in the grid."};
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

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
            if (players[0].getEnergy() == 0) {
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

        repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        //quick tutorial reset
        if (tutorialIndex == 7) {
            tutorialIndex = 0;
        }
    }

    @Override
    public void endTurn() {
        if ((players[0].getEnergy() == 0) || (turnNumber >= 2)) {
            super.endTurn();
        } else {
            tutorialIndex = 7;
            tutorialPrompt.setText(tutorial[tutorialIndex]);
        }
    }
}

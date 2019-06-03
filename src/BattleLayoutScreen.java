import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * [BattleLayoutScreen.java]
 * This screen is used to edit the starting battle layout for other levels
 * @version 1.0
 * @author Allen Liu
 * @since June 2, 2019
 */
public class BattleLayoutScreen extends GameScreen {

    String[] playerDebugNames = {"allen", "kevin", "bryan"};
    Player[] playerList;
    Player[][] grid;
    Player selectedPlayer;

    String status;

    Rectangle gridSpace;
    Rectangle playerListSpace;

    Rectangle resetButton;
    Rectangle saveButton;
    Rectangle continueButton;

    public BattleLayoutScreen(GameManager game) {
        super(game);

        Player[] currentLoadout = GameIO.getBattleLayout();

        grid = new Player[3][3];
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                grid[i][j] = null;
            }
        }

        for (int i = 0; i < currentLoadout.length; ++i) {
            Player p = currentLoadout[i];
            grid[p.getXGrid()][p.getYGrid()] = p;
        }

        playerList = new Player[playerDebugNames.length];

        for (int i = 0; i < playerDebugNames.length; ++i) {
            playerList[i] = GameIO.generatePlayer(playerDebugNames[i]);
        }

        gridSpace = new Rectangle(323, 108, 121 * 3, 121 * 3);
        playerListSpace = new Rectangle(1069, 108, 121, 121 * playerDebugNames.length);

        resetButton = new Rectangle(323, 500, 121 * 3, 60);
        saveButton = new Rectangle(323, 570, 121 * 3, 60);

        status = "Drag the players to place them on the grid.";
        continueButton = new Rectangle(323,640, 121*3, 60);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Draw the 3x3 grid
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                g.drawRect(323 + 121 * i, 108 + 121 * j, 120, 120);
                if (grid[i][j] != null) {
                    grid[i][j].draw(323 + 121 * i, 108 + 121 * j, g, false);
                    g.setColor(Color.BLACK);
                    g.drawString(grid[i][j].getName(), 323 + 121 * i + 10, 108 + 121 * j + 20);
                }
            }
        }

        //Draw the selected player if they are currently being dragged
        if (selectedPlayer != null) {
            selectedPlayer.draw(getMouseX(), getMouseY(), g, false);
            g.drawString(selectedPlayer.getName(), getMouseX() + 10, getMouseY() + 20);
        }

        //Draw reset button
        if (isMouseOver(resetButton)) {
            g.setColor(Color.GREEN);
            g.fillRect(resetButton.x, resetButton.y, resetButton.width, resetButton.height);
        } else {
            g.setColor(Color.BLACK);
            g.drawRect(resetButton.x, resetButton.y, resetButton.width, resetButton.height);
        }
        g.setColor(Color.BLACK);
        g.drawString("Reset Loadout", resetButton.x + 20, resetButton.y + 20);

        //Draw save button
        if (isMouseOver(saveButton)) {
            g.setColor(Color.GREEN);
            g.fillRect(saveButton.x, saveButton.y, saveButton.width, saveButton.height);
        } else {
            g.setColor(Color.BLACK);
            g.drawRect(saveButton.x, saveButton.y, saveButton.width, saveButton.height);
        }
        g.setColor(Color.BLACK);
        g.drawString("Save Loadout", saveButton.x + 20, saveButton.y + 20);

        //Draw the abilities of the profile of the player who is selected
        if (selectedPlayer != null) {
            selectedPlayer.drawAbilities(g, null);

            for (int i = 0; i < selectedPlayer.totalAbilities(); ++i) {
                if (isMouseOver(new Rectangle(30, 15+105*i, 263, 100))) {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(30, 15+105*i, 263, 100);
                    //Will make it easier to see which tiles can be targetable
                    /*
                    if (selectedAbility == null) {
                        jointMap.unIndicateAll();
                        jointMap.unTargetableAll();
                        selectedPlayer.getAbility(i).indicateValidTiles(jointMap);
                    }

                     */
                }
            }
        }

        //Draw status message
        g.setColor(Color.BLACK);
        g.drawString(status, 600, 100);

        for (int i = 0; i < playerDebugNames.length; ++i) {
            g.drawRect(1069, 108 + i * 121, 120, 120);
            g.drawString(playerList[i].getName() ,1069 + 10, 108 + i * 121 + 20);
        }

        //Draw continue button
        if (isMouseOver(continueButton)) {
            g.setColor(Color.GREEN);
            g.fillRect(continueButton.x, continueButton.y, continueButton.width, continueButton.height);
        } else {
            g.setColor(Color.BLACK);
            g.drawRect(continueButton.x, continueButton.y, continueButton.width, continueButton.height);
        }
        g.setColor(Color.BLACK);
        g.drawString("To Battle", continueButton.x + 20, continueButton.y + 20);

        repaint();

        if (isMouseOver(gridSpace)) {
            //Get grid x and y
            int gridX = (getMouseX() - gridSpace.x) / 121;
            int gridY = (getMouseY() - gridSpace.y) / 121;
            if (grid[gridX][gridY] != null) {
                grid[gridX][gridY].drawSelectAbilities(g);
                selectedPlayer = null;
            }
        }
    }

    //Player dragging!

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        //Drag player from grid
        if (isMouseOver(gridSpace)) {
            //Get grid x and y
            int gridX = (getMouseX() - gridSpace.x) / 121;
            int gridY = (getMouseY() - gridSpace.y) / 121;

            selectedPlayer = grid[gridX][gridY];
            grid[gridX][gridY] = null;
        }

        //Drag players from main player list
        if (isMouseOver(playerListSpace)) {
            int index = (getMouseY() - playerListSpace.y) / 121;

            selectedPlayer = playerList[index];
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        //Check for click in grid
        //Preemptive check
        if (isMouseOver(gridSpace)) {
            //Get grid x and y
            int gridX = (getMouseX() - gridSpace.x) / 121;
            int gridY = (getMouseY() - gridSpace.y) / 121;

            grid[gridX][gridY] = selectedPlayer;
            selectedPlayer = null;
        } else if (selectedPlayer != null){
            selectedPlayer = null;
        }

        //Reset loadout button
        if (isFullyClicked(resetButton)) {
            Player[] currentLoadout = GameIO.getBattleLayout();
            for (int j = 0; j < 3; ++j) {
                for (int i = 0; i < 3; ++i) {
                    grid[i][j] = null;
                }
            }

            for (int i = 0; i < currentLoadout.length; ++i) {
                Player p = currentLoadout[i];
                grid[p.getXGrid()][p.getYGrid()] = p;
            }
        }


        //Save layout button
        if (isFullyClicked(saveButton)) {
            //Do a count to make sure there is a sane number of players
            int numPlayers = 0;
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    if (grid[x][y] != null) {
                        numPlayers++;
                    }
                }
            }
            if (numPlayers == 3) {
                GameIO.setBattleLayout(grid);
                status = "Loadout saved.";
            } else {
                status = "You need 3 players to save a loadout!";
            }
        }

        if (isFullyClicked(continueButton)) {
            //This piece of code to transition to levelScreen is completely experimental
            getGame().setScreen(new LevelScreen(getGame()));
        }
    }
}

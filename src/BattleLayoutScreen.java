import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 * [BattleLayoutScreen.java]
 * This screen is used to edit the starting battle layout for other levels
 * @version 1.2
 * @author Allen Liu
 * @since June 13, 2019
 */
public class BattleLayoutScreen extends GameScreen {

    private String[] playerDebugNames = {"allen", "kevin", "jasmine", "ethan"};
    private Player[] playerList;
    private Player[][] grid;
    private Player selectedPlayer;

    private String status;

    private Rectangle gridSpace;
    private Rectangle playerListSpace;

    private Rectangle resetButton;
    private Rectangle saveButton;
    private Rectangle continueButton;

    private LevelScreen nextLevel;

    /**
     * [BattleLayoutScreen]
     * create a battle layout screen that will then advance to a specified level
     * @param game the game that the screen is made in
     * @param level the level to progress to after selection
     */
    public BattleLayoutScreen(GameManager game, LevelScreen level) {
        super(game);

        nextLevel = level;

        grid = new Player[3][3];

        initializeGrid();

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

    /**
     * [paintComponent]
     * draws the grid, player selection, and save/reset buttons for loadout editing
     * @param g the graphics object to draw with
     */
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

        //Draw buttons
        drawButton(g, resetButton, "Reset Loadout");
        drawButton(g, saveButton, "Save Loadout");
        drawButton(g, continueButton, "To Battle");

        drawSelectedPlayer(g);

        //Draw status message
        g.setColor(Color.BLACK);
        g.drawString(status, 600, 100);


        //Draw list of ALL players
        for (int i = 0; i < playerDebugNames.length; ++i) {
            playerList[i].draw(1069, 108 + i * 121, g, false);
            g.drawString(playerList[i].getName() ,1069 + 10, 108 + i * 121 + 20);
        }

        if (isMouseOver(gridSpace)) {
            //Get grid x and y
            int gridX = (getMouseX() - gridSpace.x) / 121;
            int gridY = (getMouseY() - gridSpace.y) / 121;
            if (grid[gridX][gridY] != null) {
                grid[gridX][gridY].drawSelectAbilities(g);
            }
        }

        repaint();
    }

    //Player dragging!

    /**
     * [mousePressed]
     * checks if the mouse is pressed, and allows for a player to be 'selected' if the mouse is pressed over them
     * @param e the triggered MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        //Drag player from grid
        if (isMouseOver(gridSpace)) {
            //Get grid x and y
            int gridX = (getMouseX() - gridSpace.x) / 121;
            int gridY = (getMouseY() - gridSpace.y) / 121;

            //'grabs' the player from the tile space
            selectedPlayer = grid[gridX][gridY];
            grid[gridX][gridY] = null;
        }

        //Drag players from main player list
        if (isMouseOver(playerListSpace)) {
            int index = (getMouseY() - playerListSpace.y) / 121;

            selectedPlayer = playerList[index];
        }
    }

    /**
     * [mouseReleased]
     * Runs when the mouse is released, 'dropping' the player onto a grid tile (if possible), deselecting them.
     * Also checks for button presses to reset and save loadout.
     * @param e the triggered MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        //Check for click in grid
        //Preemptive check
        if (isMouseOver(gridSpace)) {
            //Get grid x and y
            int gridX = (getMouseX() - gridSpace.x) / 121;
            int gridY = (getMouseY() - gridSpace.y) / 121;

            //You can drop up to 3 characters, or replace one if you have 3 on teh grid already
            if (((!isPlayerInGrid(selectedPlayer)) && ((getNumPlayersInGrid() < 3)) || (grid[gridX][gridY] != null))) {
                //'drop' player into grid tile
                grid[gridX][gridY] = selectedPlayer;
                selectedPlayer = null;
            } else {
                if (getNumPlayersInGrid() >= 3) {
                    status = "You have reached the maximum number of players in that loadout!";
                } else {
                    status = "That player is already on the grid!";
                }
                selectedPlayer = null;
            }
            //If the selected player is 'dropped' anywhere else
        } else if (selectedPlayer != null){
            selectedPlayer = null;
        }

        //Reset loadout button
        if (isFullyClicked(resetButton)) {
            initializeGrid();
        }

        //Save layout button
        if (isFullyClicked(saveButton)) {
            //Do a count to make sure there is a sane number of players
            if (getNumPlayersInGrid() == 3) {
                GameIO.setBattleLayout(grid);
                status = "Loadout saved.";
            } else {
                status = "You need 3 players to save a loadout!";
            }
        }

        if (isFullyClicked(continueButton)) {
            //This piece of code to transition to levelScreen is completely experimental
            if (getNumPlayersInGrid() == 3) {
                GameIO.setBattleLayout(grid);
                status = "Loadout saved.";
                getGame().setScreen(nextLevel);
            } else {
                status = "You need 3 players to save a loadout!";
            }
            /*
            getGame().setScreen(new LevelScreen(getGame(), new Enemy[] {
                    new DecaEnemy(5,2),
                    new FlaskEnemy(5,0),
                    new ProcessingCloudEnemy(3, 1)
            }));
             */
        }
    }

    /**
     * [isPlayerInGrid]
     * checks if a player is in the battle loadout grid
     * @return boolean, whether the specified player is in the loadout grid
     */
    private boolean isPlayerInGrid(Player p) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                //In theory, both players should be equal, but since one has a position, two 'identical' people
                //would be considered unequal
                if ((grid[x][y] != null) && (grid[x][y].getDebugName().equals(p.getDebugName()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * [getNumPlayersInGrid]
     * gets the number of players in the loadout grid
     * @return int, the number of players in the current loadout
     */
    private int getNumPlayersInGrid() {
        int numPlayers = 0;
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                if (grid[x][y] != null) {
                    numPlayers++;
                }
            }
        }
        return numPlayers;
    }

    /**
     * [drawButton]
     * draws a rectangle as a simple button, which will be drawn empty by default, and will be filled green if it is
     * hovered over
     * @param g the graphics object to draw with
     * @param rect the rectangle to draw as a button
     * @param text the text to draw within the button
     */
    private void drawButton(Graphics g, Rectangle rect, String text) {
        int lineWidth = 1;

        g.setColor(Color.BLACK);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);

        if (isMouseOver(rect)) {
            g.setColor(Color.GREEN);
            g.fillRect(rect.x + lineWidth, rect.y + lineWidth, rect.width - lineWidth, rect.height - lineWidth);
        }

        g.setColor(Color.BLACK);
        g.drawString(text, rect.x + 10, rect.y + 20);
    }

    /**
     * [drawSelectedPlayer]
     * draws the currently selected player, along with their ability list and profile
     * @param g the graphics object to draw with
     */
    private void drawSelectedPlayer(Graphics g) {
        //Draw the selected player if they are currently being dragged
        if (selectedPlayer != null) {
            selectedPlayer.draw(getMouseX(), getMouseY(), g, false);
            g.drawString(selectedPlayer.getName(), getMouseX() + 10, getMouseY() + 20);
        }

        //Draw the abilities of the profile of the player who is selected
        if (selectedPlayer != null) {
            selectedPlayer.drawAbilities(g, null);

            for (int i = 0; i < selectedPlayer.totalAbilities(); ++i) {
                if (isMouseOver(new Rectangle(30, 15+105*i, 263, 100))) {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(30, 15+105*i, 263, 100);
                }
            }
        }
    }

    /**
     * [initializeGrid]
     * initializes the grid of players by clearing it, and then adding players from an internal file
     */
    private void initializeGrid() {
        Player[] currentLoadout = GameIO.getBattleLayout();

        for (int i = 0; i < currentLoadout.length; ++i) {
            Player p = currentLoadout[i];
            grid[p.getXGrid()][p.getYGrid()] = p;
        }
    }
}

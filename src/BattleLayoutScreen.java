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

    String[] playerDebugNames;
    Player[][] grid;
    Player selectedPlayer;

    Rectangle gridSpace;
    Rectangle resetButton;
    Rectangle saveButton;
    Rectangle continueButton;

    public BattleLayoutScreen(GameManager game) {
        super(game);

        Player[] currentLoadout = GameIO.getBattleLayout();

        gridSpace = new Rectangle(323, 108, 121 * 3, 121 * 3);

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

        resetButton = new Rectangle(323, 500, 121 * 3, 60);
        saveButton = new Rectangle(323, 570, 121 * 3, 60);
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
                    g.drawString(grid[i][j].getName(), 323 + 121 * i, 108 + 121 * j + 20);
                }
            }
        }

        //Draw the selected player if they are currently being dragged
        if (selectedPlayer != null) {
            selectedPlayer.draw(getMouseX(), getMouseY(), g, false);
            g.drawString(selectedPlayer.getName(), getMouseX(), getMouseY() + 20);
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
        }

        ////Reset loadout button
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
            GameIO.setBattleLayout(grid);
        }

        if (isFullyClicked(continueButton)) {
            //This piece of code to transition to levelScreen is completely experimental
            getGame().setScreen(new LevelScreen(getGame()));
        }
    }
}

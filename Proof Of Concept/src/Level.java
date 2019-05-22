import java.awt.*;
import java.awt.event.MouseEvent;

public class Level extends GameScreen {

    PlayerTile[][] playerTiles;
    EnemyTile[][] enemyTiles;

    public Level(GameManager game) {
        super(game);
        playerTiles = new PlayerTile[3][3];
        enemyTiles = new EnemyTile[3][3];

        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                playerTiles[x][y] = new PlayerTile();
                playerTiles[x][y].setBoundingBox(new Rectangle(200 + 80 * x, 200 + 80 * y,80, 80));
                enemyTiles[x][y] = new EnemyTile();
                enemyTiles[x][y].setBoundingBox(new Rectangle(600 + 80 * x, 200 + 80 * y,80, 80));
            }
        }

        enemyTiles[0][0].setEntity(new Enemy("DEBUG", 1, 1, 1, 1));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.getFontRenderContext();

        g.setColor(Color.BLACK);
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                playerTiles[x][y].draw(g);
                enemyTiles[x][y].draw(g);
            }
        }

        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                playerTiles[x][y].draw(g);
                enemyTiles[x][y].draw(g);
                if (this.isMouseOver(playerTiles[x][y].getBoundingBox())) {
                    playerTiles[x][y].drawSpecial(g);
                }
                if (this.isMouseOver(enemyTiles[x][y].getBoundingBox())) {
                    enemyTiles[x][y].drawSpecial(g);

                }
                if (playerTiles[x][y].getEntity() != null) {
                    playerTiles[x][y].getEntity().draw(g);
                }
                if (enemyTiles[x][y].getEntity() != null) {
                    enemyTiles[x][y].getEntity().draw(g);
                }
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        //enemyTiles[0][0].getEnemy().act();
    }
}

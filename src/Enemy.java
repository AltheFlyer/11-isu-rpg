import java.awt.*;

public class Enemy extends Entity{

    private Icon testIntent;

    Enemy(double health, String name, Ability ability1){
        super(health,name,ability1);
        ability1.setEntitySource(this);
        testIntent = new Icon(new Rectangle(0, 0, 40, 40), "assets/icons/test.png");
        testIntent.setName("Special");
    }

    public Ability getAbility1(){
        return ability1;
    }

    public void drawAbilities(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0,0,120,120);
    }

    public void draw(int x, int y, Graphics g, boolean indicated){
        g.setColor(Color.ORANGE);
        g.fillRect(x,y,120,120);
        g.setColor(Color.BLACK);
        if (indicated){
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(x,y,120,120);
        }
        g.drawRect(x, y, 120, 120);
    }

    public Icon getIntent() {
        return testIntent;
    }
}

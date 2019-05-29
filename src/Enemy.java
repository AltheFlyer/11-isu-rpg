import java.awt.*;

public class Enemy extends Entity{

    private Icon testIntent;
    private Ability action;

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

    /**
     * [act]
     * contrary to the method name, this doesn't cause enemies to act, rather it
     * generates a next move (Ability and intent) (should be overriden)
     */
    public void act() {
        //For testing/theory purposes only...
        /*
       if (this.getHealth() < this.maxHealth() / 2) {
            intent = new Icon(new Rectangle(), "assets/icons/crush");
            ability = abilities[1];
       } else {
            intent = new Icon(new Rectangle(), "assets/icons/attack");
            ability = abilities[0];
       }

         */
    }

    public Icon getIntent() {
        return testIntent;
    }

    public Ability getAction() {
        return action;
    }
}

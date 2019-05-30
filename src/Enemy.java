import java.awt.*;

public class Enemy extends Entity{

    private Icon testIntent;
    private Ability decide;

    Enemy(double health, String name, Ability[] abilities){
        super(health,name,abilities);
        for (int i = 0; i < abilities.length; i++){
            abilities[i].setEntitySource(this);
        }
        //The first ability is always the one that will be decided to be used first
        decide = abilities[0];
        testIntent = new Icon(new Rectangle(0, 0, 40, 40), "assets/icons/test.png");
        testIntent.setName("Special");
        testIntent.setDescription("A *really* powerful attack. I need more text to test newline drawing.\n\n\n\n\n\nI hope this works");
    }

    public Ability getAbility(int index){
        return abilities[index];
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
     * [decide]
     * contrary to the method name, this doesn't cause enemies to decide, rather it
     * generates a next move (Ability and intent) (should be overriden)
     */
    public void decide(JointMap map) {
        //For testing/theory purposes only...
        if (this.getHealth() < this.getMaxHealth() / 2) {
            //A heal on itself
            testIntent = new Icon("assets/icons/medic.png");
            testIntent.setName("Medic");
            testIntent.setDescription("This enemy intends to restore health to itself.");
            //I don't know if there are multiple attacks yet so...
            decide = abilities[1];
        } else {
            testIntent = new Icon("assets/icons/sword.png");
            testIntent.setName("Attack");
            testIntent.setDescription("A basic attack that will deal damage to a player.");
            decide = abilities[0];
        }

    }

    public Icon getIntent() {
        return testIntent;
    }

    public Ability getDecide() {
        return decide;
    }



}

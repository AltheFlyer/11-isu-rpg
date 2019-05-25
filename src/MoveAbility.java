public class MoveAbility extends Ability{
    private int moves;
    private int direction;
    MoveAbility(String name, int moves){
        super(name, moves);
    }

    public void movePlayer(PlayerMap playerMap, int x, int y, int xNow, int yNow){
        playerMap.swapTiles(x,y,xNow,yNow);
    }

    public void moveEnemy(){

    }
}

import java.util.HashMap;

public class IOTester {

    public static void main(String[] args) {
        GameIO.setTileWalkability("maps/tile_walkability.txt");
        testBattleLayout();
        testInventory();
        testMap();
    }

    static void testBattleLayout() {
        //Reading
        String[][] tab1 = GameIO.getBattleLayout();
        String result1 = "";
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x< 3; ++x) {
                result1 += tab1[x][y] + " ";
            }
            result1 += "\n";
        }

        System.out.println(result1);
        String[][] tab2 = {{"*", "*", "*"}, {"a", "b", "c_c_c_equals_c_cubed"}, {"*", "*", "*"}};
        GameIO.setBattleLayout(tab2);
    }

    static void testInventory() {
        System.out.println(GameIO.getInventory());

        HashMap<String, Integer> tab2 = new HashMap<>();

        tab2.put("bone_hurting_juice", 10);
        tab2.put("red", 2);
        tab2.put("holy_hand_grenade", 5);

        GameIO.setInventory(tab2);
    }

    static void testMap() {
        OverworldTile[][] map = GameIO.getMap("/maps/test.txt");
        for (int y = 0; y < map[0].length; ++y) {
            for (int x = 0; x < map.length; ++x) {
                System.out.print(map[x][y].isWalkable + " ");
            }
            System.out.println();
        }
    }
}

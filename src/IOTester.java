import java.util.HashMap;

public class IOTester {

    public static void main(String[] args) {
        GameIO.readTileWalkability("tile_walkability.txt");
        testBattleLayout();
        testInventory();
        testMap();
        testAccess();
        testPlayers();
    }

    static void testPlayers() {
        GameIO.getPlayer("players/test_player.txt");
    }

    static void testAccess() {
        //ABILITY UNLOCKS
        GameIO.readAbilityUnlocks();
        System.out.println(GameIO.getAbilityUnlocks());
        HashMap<String, Boolean> stringBooleanHashMap = new HashMap<>();
        stringBooleanHashMap.put("recurse", true);
        stringBooleanHashMap.put("sing", true);
        stringBooleanHashMap.put("instant_die", false);
        GameIO.setAbilityUnlocks(stringBooleanHashMap);
        GameIO.writeAbilityUnlocks();

        //EQUIP UNLOCKS
        GameIO.readEquipUnlocks();
        System.out.println(GameIO.getEquipUnlocks());
        stringBooleanHashMap = new HashMap<>();
        stringBooleanHashMap.put("helmet", true);
        stringBooleanHashMap.put("zweihander", true);
        stringBooleanHashMap.put("trihander", false);
        GameIO.setEquipUnlocks(stringBooleanHashMap);
        GameIO.writeEquipUnlocks();

        //LEVEL PROGRESSION
        GameIO.readLevelCompletion();
        System.out.println(GameIO.getLevelCompletion());
        stringBooleanHashMap = new HashMap<>();
        stringBooleanHashMap.put("period1_a", true);
        stringBooleanHashMap.put("period2_a", true);
        stringBooleanHashMap.put("period3_a", false);
        GameIO.setLevelCompletion(stringBooleanHashMap);
        GameIO.writeLevelCompletion();

        //TIME STATE
        GameIO.readTimeState();
        System.out.println(GameIO.getCurrentDay() + " " + GameIO.getCurrentPeriod());
        GameIO.setTimeState(10, 4);
        GameIO.writeTimeState();
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
        GameIO.readInventory();
        System.out.println(GameIO.getInventory());

        HashMap<String, Integer> tab2 = new HashMap<>();

        tab2.put("bone_hurting_juice", 10);
        tab2.put("red", 2);
        tab2.put("holy_hand_grenade", 5);

        GameIO.setInventory(tab2);
        GameIO.writeInventory();
    }

    static void testMap() {
        OverworldTile[][] map = GameIO.getMap("test.txt", 100);
        for (int y = 0; y < map[0].length; ++y) {
            for (int x = 0; x < map.length; ++x) {
                System.out.print(map[x][y].isWalkable() + " ");
            }
            System.out.println();
        }
    }
}

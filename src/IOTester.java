import java.util.HashMap;

public class IOTester {

    public static void main(String[] args) {
        DeprecatedGameIO.readTileWalkability("tile_walkability.txt");
        testBattleLayout();
        testInventory();
        testMap();
        testAccess();
        testPlayers();
    }

    static void testPlayers() {
        DeprecatedGameIO.getPlayer("players/test_player.txt");
    }

    static void testAccess() {
        //ABILITY UNLOCKS
        DeprecatedGameIO.readAbilityUnlocks();
        System.out.println(DeprecatedGameIO.getAbilityUnlocks());
        HashMap<String, Boolean> stringBooleanHashMap = new HashMap<>();
        stringBooleanHashMap.put("recurse", true);
        stringBooleanHashMap.put("sing", true);
        stringBooleanHashMap.put("instant_die", false);
        DeprecatedGameIO.setAbilityUnlocks(stringBooleanHashMap);
        DeprecatedGameIO.writeAbilityUnlocks();

        //EQUIP UNLOCKS
        DeprecatedGameIO.readEquipUnlocks();
        System.out.println(DeprecatedGameIO.getEquipUnlocks());
        stringBooleanHashMap = new HashMap<>();
        stringBooleanHashMap.put("helmet", true);
        stringBooleanHashMap.put("zweihander", true);
        stringBooleanHashMap.put("trihander", false);
        DeprecatedGameIO.setEquipUnlocks(stringBooleanHashMap);
        DeprecatedGameIO.writeEquipUnlocks();

        //LEVEL PROGRESSION
        DeprecatedGameIO.readLevelCompletion();
        System.out.println(DeprecatedGameIO.getLevelCompletion());
        stringBooleanHashMap = new HashMap<>();
        stringBooleanHashMap.put("period1_a", true);
        stringBooleanHashMap.put("period2_a", true);
        stringBooleanHashMap.put("period3_a", false);
        DeprecatedGameIO.setLevelCompletion(stringBooleanHashMap);
        DeprecatedGameIO.writeLevelCompletion();

        //TIME STATE
        DeprecatedGameIO.readTimeState();
        System.out.println(DeprecatedGameIO.getCurrentDay() + " " + DeprecatedGameIO.getCurrentPeriod());
        DeprecatedGameIO.setTimeState(10, 4);
        DeprecatedGameIO.writeTimeState();
    }

    static void testBattleLayout() {
        //Reading
        String[][] tab1 = DeprecatedGameIO.getBattleLayout();
        String result1 = "";
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x< 3; ++x) {
                result1 += tab1[x][y] + " ";
            }
            result1 += "\n";
        }

        System.out.println(result1);
        String[][] tab2 = {{"*", "*", "*"}, {"a", "b", "c_c_c_equals_c_cubed"}, {"*", "*", "*"}};
        DeprecatedGameIO.setBattleLayout(tab2);
    }

    static void testInventory() {
        DeprecatedGameIO.readInventory();
        System.out.println(DeprecatedGameIO.getInventory());

        HashMap<String, Integer> tab2 = new HashMap<>();

        tab2.put("bone_hurting_juice", 10);
        tab2.put("red", 2);
        tab2.put("holy_hand_grenade", 5);

        DeprecatedGameIO.setInventory(tab2);
        DeprecatedGameIO.writeInventory();
    }

    static void testMap() {
        OverworldTile[][] map = DeprecatedGameIO.getMap("test.txt", 100);
        for (int y = 0; y < map[0].length; ++y) {
            for (int x = 0; x < map.length; ++x) {
                System.out.print(map[x][y].isWalkable() + " ");
            }
            System.out.println();
        }
    }
}

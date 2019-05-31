import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [DeprecatedGameIO.java]
 * static class that manages IO with text files for progression and saved data
 * @version 1.0
 * @author Allen Liu
 * @since May 23, 2019
 */
public class DeprecatedGameIO {

    /**
     * The string of the folder that all text files are to be placed in,
     * acting as a universal accessor
     */
    private static final String TEXT_SRC = "data/";

    private static HashMap<String, Boolean> tileWalkability;

    //Dynamic game data
    private static HashMap<String, Boolean> abilityUnlocks;

    private static HashMap<String, Boolean> levelCompletion;
    private static HashMap<String, Boolean> equipUnlocks;
    private static HashMap<String, Integer> inventory;

    private static int currentDay;
    private static int currentPeriod;

    //Game initialization (file reading) methods

    /**
     * [readTileWalkability]
     * loads all tile types, and saves whether they can be walked on or not
     * @param path the file path of the text file with all walkability values, not including source folder
     */
    public static void readTileWalkability(String path) {
        tileWalkability = new HashMap();
        String allData = readFile(path);

        String[] lines = allData.split("\n");

        for (int i = 0; i < lines.length; ++i) {
            int spaceIndex = lines[i].indexOf(" ");
            tileWalkability.put(lines[i].substring(0, spaceIndex), Boolean.valueOf(lines[i].substring(spaceIndex + 1)));
        }
    }

    /**
     * [readTimeState]
     * Gets the current ingame period and day
     */
    public static void readTimeState() {
        String lines = readFile("progression/time_state.txt");

        //2 lines in file
        int newline = lines.indexOf("\n");
        String periodLine = lines.substring(0, newline);
        String dayLine = lines.substring(newline + 1);

        currentPeriod = Integer.parseInt(periodLine.substring(periodLine.indexOf(" ") + 1));
        currentDay = Integer.parseInt(dayLine.substring(dayLine.indexOf(" ") + 1));
    }

    /**
     * [readLevelCompletion]
     * reads the current state of level completion
     */
    public static void readLevelCompletion() {
        levelCompletion = readFileAsHashMap("progression/level_completion.txt");
    }

    /**
     * [readAbilityUnlocks]
     * reads the currently unlocked abilities - used to find whether a character can use one or not
     */
    public static void readAbilityUnlocks() {
        abilityUnlocks = readFileAsHashMap("progression/ability_unlock.txt");
    }

    /**
     * [readEquipUnlocks]
     * reads the currently unlocked equips
     */
    public static void readEquipUnlocks() {
        equipUnlocks = readFileAsHashMap("progression/equip_unlock.txt");
    }

    /**
     * [readInventory]
     * gets the saved inventory from the inventory file
     * @return HashMap (String, Integer) containing a list of items in the inventory, along with the count of each item
     */
    public static void readInventory() {
        String fullText = readFile("inventory.txt");
        inventory = new HashMap();

        String[] lines = fullText.split("\n");
        for (int i = 0; i < lines.length; ++i) {
            int spacer = lines[i].indexOf(" ");
            inventory.put(lines[i].substring(0, spacer), Integer.parseInt(lines[i].substring(spacer + 1)));
        }
    }


    //HashMap access

    /**
     * [getInventory]
     * gets the saved inventory (not necessarily what's saved)
     * @return HashMap String - Integer representing the inventory
     */
    public static HashMap<String, Integer> getInventory() {
        return inventory;
    }

    /**
     * [getTileWalkability]
     * gets the tile walkability data(not necessarily what's saved)
     * @return HashMap String - Boolean representing whether each tile is walkable
     */
    public static HashMap<String, Boolean> getTileWalkability() {
        return tileWalkability;
    }

    /**
     * [getAbilityUnlocks]
     * gets the list of unlocked abilities
     * @return HashMap String - Boolean representing whether an ability is unlocked or not
     */
    public static HashMap<String, Boolean> getAbilityUnlocks() {
        return abilityUnlocks;
    }

    /**
     * [getLevelCompletion]
     * gets the list of completed levels
     * @return HashMap String - Boolean representing whether a level is completed or not
     */
    public static HashMap<String, Boolean> getLevelCompletion() {
        return levelCompletion;
    }

    /**
     * [getEquipUnlocks]
     * gets the list of unlocked equipment
     * @return HashMap String - Boolean representing whether an equip is unlocked or not
     */
    public static HashMap<String, Boolean> getEquipUnlocks() {
        return equipUnlocks;
    }

    /**
     * [getCurrentDay]
     * gets the current day in game
     * @return int, the current day
     */
    public static int getCurrentDay() {
        return currentDay;
    }

    /**
     * [getCurrentPeriod]
     * gets the current period in game
     * @return int, the current period
     */
    public static int getCurrentPeriod() {
        return currentPeriod;
    }


    //Dynamic setters

    /**
     * [setAbilityUnlocks]
     * sets the (in-memory) stored abilities
     * use a write method to save it to an internal file
     * @param abilityUnlocks a Hashmap representing locked/unlocked abilities
     */
    public static void setAbilityUnlocks(HashMap<String, Boolean> abilityUnlocks) {
        DeprecatedGameIO.abilityUnlocks = abilityUnlocks;
    }

    /**
     * [setLevelCompletion]
     * sets the (in-memory) stored level completion
     * use a write method to save it to an internal file
     * @param levelCompletion a Hashmap representing finished/unfinished levels
     */
    public static void setLevelCompletion(HashMap<String, Boolean> levelCompletion) {
        DeprecatedGameIO.levelCompletion = levelCompletion;
    }

    /**
     * [setEquipUnlocks]
     * sets the (in-memory) stored equip unlocks
     * use a write method to save it to an internal file
     * @param equipUnlocks a Hashmap representing locked/unlocked equips
     */
    public static void setEquipUnlocks(HashMap<String, Boolean> equipUnlocks) {
        DeprecatedGameIO.equipUnlocks = equipUnlocks;
    }

    /**
     * [setInventory]
     * sets the (in-memory) inventory
     * use a write method to save it to an internal file
     * @param inventory a Hashmap representing items and a count of each
     */
    public static void setInventory(HashMap<String, Integer> inventory) {
        DeprecatedGameIO.inventory = inventory;
    }

    /**
     * [setTimeState]
     * sets the (in-memory) time state
     * @param period the current ingame period
     * @param day the current ingame day
     */
    public static void setTimeState(int period, int day) {
        currentPeriod = period;
        currentDay = day;
    }


    //Dynamic file writing

    /**
     * [writeTimeState]
     * writes the in game time and period to an internal file
     */
    public static void writeTimeState() {
        writeFile("progression/time_state.txt", String.format("current_period %d\ncurrent_day %d", currentPeriod, currentDay));
    }

    /**
     * [writeInventory]
     * writes the dynamic inventory to the internal inventory file
     */
    public static void writeInventory() {
        writeHashMapToFile("inventory.txt", inventory);
    }

    /**
     * [writeLevelCompletion]
     * writes the dynamic level completion to an internal file
     */
    public static void writeLevelCompletion() {
        writeHashMapToFile("progression/level_completion.txt", levelCompletion);
    }

    /**
     * [writeEquipUnlocks]
     * writes the dynamic equipment unlocks to an internal file
     */
    public static void writeEquipUnlocks() {
        writeHashMapToFile("progression/equip_unlock.txt", equipUnlocks);
    }

    /**
     * [writeAbilityUnlocks]
     * writes the dynamic ability unlocks to an internal file
     */
    public static void writeAbilityUnlocks() {
        writeHashMapToFile("progression/ability_unlock.txt", abilityUnlocks);
    }



    //Battle Layouts (for levels)

    /**
     * [getBattleLayout]
     * Gets the saved character layout on a 3x3 grid for battles from a file.
     * @return String[][] the 3x3 arrangement of characters
     */
    public static String[][] getBattleLayout() {
        String text = readFile("battle_layout.txt");
        String[][] data = new String[3][3];

        for (int i = 0; i < 3; ++i) {
            int lineBreak = text.indexOf("\n");
            //Final line reading (no trailing newline)
            if (lineBreak == -1) {
                lineBreak = text.length();
            }

            String row = text.substring(0, lineBreak);
            //Don't prep for next line if it don't exist
            if (i < 2) {
                text = text.substring(lineBreak + 1);
            }

            String[] set = row.split(" ");

            for (int j = 0; j < 3; ++j) {
                data[j][i] = set[j];
            }
        }

        return data;
    }

    /**
     * [setBattleLayout]
     * Saves a 3x3 battle layout for later use. The grid is expected to have only 3 characters.
     * @param grid the 3x3 grid of players to save
     */
    public static void setBattleLayout(String[][] grid) {
        String toWrite = "";
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                toWrite += grid[x][y];
                if (x != 2) {
                    toWrite += " ";
                }
            }
            toWrite += "\n";
        }

        writeFile("battle_layout.txt", toWrite);
        System.out.println(toWrite);
    }


    //Player file reading

    /**
     * [getPlayer]
     * gets player data from a specified file
     * THIS METHOD IS NOT COMPLETE, WILL BE UPDATED WHEN PLAYER CLASS IS COMPLETE
     * @param path the file to read from, not including source folder
     * @return String, the player data as a String **NOTE: THIS WILL CHANGE SOON**
     */
    public static String getPlayer(String path) {
        String allText = readFile(path);
        String[] lines = allText.split("\n");

        //Name line
        String fullName = cutFirstWord(lines[0]);
        //Health
        int maxHealth = Integer.parseInt(cutFirstWord(lines[1]));
        //Energy
        int baseEnergy = Integer.parseInt(cutFirstWord(lines[2]));
        //Attack
        int baseAttack = Integer.parseInt(cutFirstWord(lines[3]));
        //Defense
        int baseDefense = Integer.parseInt(cutFirstWord(lines[4]));

        //Line 5 is for human readability
        int numEquips = 3;
        int lineNumber = 6;

        String[] equips = new String[numEquips];

        for (int i = 0; i < numEquips; ++i) {
            equips[i] = cutFirstWord(lines[lineNumber]);
            lineNumber++;
        }

        //Line 9 is for human readability and parsing
        lineNumber = 9;
        int numAbilities = Integer.parseInt(cutFirstWord(lines[lineNumber]));
        lineNumber++;

        String[] abilities = new String[numAbilities];
        for (int i = 0; i < numAbilities; ++i) {
            abilities[i] = lines[lineNumber];
            lineNumber++;
        }

        System.out.println();

        return "";
    }

    /**
     * [setPlayer]
     * sets the player data in a specified file
     * THIS METHOD IS NOT COMPLETE, WILL BE UPDATED WHEN PLAYER CLASS IS COMPLETE
     * @param path the file to write to, not including source folder
     * @param data the character data as a string **NOTE: THIS WILL CHANGE SOON**
     */
    public static void setPlayer(String path, String data) {
        writeFile(path, data);
    }

    //Map based methods

    /**
     * [getMap]
     * Gets a 2d array of tiles from a specified map file.
     * WARNING: readTileWalkability must be called first in order to establish walkabilities of each given tile.
     * @param path the path of the map file (not including source folder)
     * @return OverworldTile[][] a 2d array of form [x][y] of Overworld tiles
     */
    public static OverworldTile[][] getMap(String path, int tileSize) {
        String mapText = readFile(path);
        OverworldTile[][] tileMap;

        String[] lines = mapText.split("\n");

        String[] tokens = lines[0].split(" ");
        int width = Integer.parseInt(tokens[0]);
        int height = Integer.parseInt(tokens[1]);
        tileMap = new OverworldTile[width][height];

        for (int y = 0; y < height; ++y) {
            tokens = lines[y + 1].split(" ");
            for (int x = 0; x < width; ++x) {
                tileMap[x][y] = new OverworldTile(x, y, tileWalkability.get(tokens[x]),tileSize);
            }
        }

        return tileMap;
    }

    /**
     * [getMapAsString]
     * Gets a 2d array of strings representing tiles from a specified map tile
     * NOTE: readTileWalkability need not be called to get the tile names.
     * @param path the path of the map file (not including source folder)
     * @return String[][], the array of map tiles as strings
     */
    public static String[][] getMapAsString(String path) {
        String mapText = readFile(path);
        String[][] tileMap;

        String[] lines = mapText.split("\n");

        String[] tokens = lines[0].split(" ");
        int width = Integer.parseInt(tokens[0]);
        int height = Integer.parseInt(tokens[1]);
        tileMap = new String[width][height];

        for (int y = 0; y < height; ++y) {
            tokens = lines[y + 1].split(" ");
            for (int x = 0; x < width; ++x) {
                tileMap[x][y] = tokens[x];
            }
        }

        return tileMap;
    }


    //Private File Interaction

    /**
     * [readFile]
     * reads an entire file from the files source folder
     * @param path the file path, ignoring source folder
     * @return String, the text within the file (to be parsed in other methods)
     */
    private static String readFile(String path){
        String text = "";
        try {
            BufferedReader input = new BufferedReader(new FileReader(new File(TEXT_SRC + path)));
            while (input.ready()) {
                text += input.readLine();
                if (input.ready()) {
                    text += "\n";
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    /**
     * [writeFile]
     * writes (clearing) afile in the file source folder
     * @param path the location of the file (excluding source folder)
     * @param s the text to write to the file
     */
    private static void writeFile(String path, String s) {
        try {
            FileWriter output = new FileWriter(new File(TEXT_SRC + path));
            output.write(s);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * [readFileAsHashMap]
     * reads a file as a String, Boolean hashmap
     * @param path the file to read from, not including source folder
     * @return HashMap of String to Boolean
     */
    private static HashMap<String, Boolean> readFileAsHashMap(String path) {
        String fullText = readFile(path);
        HashMap<String, Boolean> map = new HashMap<>();

        String[] lines = fullText.split("\n");
        for (int i = 0; i < lines.length; ++i) {
            int spacer = lines[i].indexOf(" ");
            map.put(lines[i].substring(0, spacer), Boolean.valueOf(lines[i].substring(spacer + 1)));
        }

        return map;
    }

    /**
     * [writeHashMapToFile]
     * @param path the file path to write to
     * @param map the hashmap to write in form : key value\n...
     */
    private static void writeHashMapToFile(String path, HashMap<?, ?> map) {
        ArrayList keys = new ArrayList<>(map.keySet());
        ArrayList values = new ArrayList<>(map.values());

        String toWrite = "";

        for (int i = 0; i < map.size(); ++i) {
            toWrite += keys.get(i) + " " + values.get(i);
            //Prevent trailing newline
            if (i != map.size() - 1) {
                toWrite += "\n";
            }
        }

        writeFile(path, toWrite);
    }

    /**
     * [cutFirstWord]
     * returns a string with the first word removed
     * @param s the string to get the words from
     * @return String, the input string with the first word (everything up to the first space) removed
     */
    private static String cutFirstWord(String s) {
        return s.substring(s.indexOf(" ") + 1);
    }
}

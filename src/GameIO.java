import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [GameIO.java]
 * static class that manages IO with text files for progression and saved data
 * TODO Change BattleLayouts to use a 2D Player array
 * @version 1.0
 * @author Allen Liu
 * @since May 23, 2019
 */
public class GameIO {

    /**
     * The string of the folder that all text files are to be placed in,
     * acting as a universal accessor
     */
    private final static String TEXT_SRC = "data/";

    private static HashMap<String, Boolean> tileWalkability;

    //Dynamic game data
    private static HashMap<String, Boolean> abilityUnlocks;

    private static HashMap<String, Boolean> levelCompletion;
    private static HashMap<String, Boolean> equipUnlocks;
    private static HashMap<String, Integer> inventory;

    private static int currentDay;
    private static int currentPeriod;

    //Game initialization methods

    /**
     * [readTileWalkability]
     * loads all tile types, and saves whether they can be walked on or not
     * @param path the file path of the text file with all walkability values, not including source folder
     */
    static public void readTileWalkability(String path) {
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
    static public void readTimeState() {
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
    static public void readLevelCompletion() {
        levelCompletion = readFileAsHashMap("progression/level_completion.txt");
    }

    /**
     * [readAbilityUnlocks]
     * reads the currently unlocked abilities - used to find whether a character can use one or not
     */
    static public void readAbilityUnlocks() {
        abilityUnlocks = readFileAsHashMap("progression/ability_unlock.txt");
    }

    /**
     * [readEquipUnlocks]
     * reads the currently unlocked equips
     */
    static public void readEquipUnlocks() {
        equipUnlocks = readFileAsHashMap("progression/equip_unlock.txt");
    }

    /**
     * [readInventory]
     * gets the saved inventory from the inventory file
     * @return HashMap (String, Integer) containing a list of items in the inventory, along with the count of each item
     */
    static public void readInventory() {
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
    static public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    /**
     * [getTileWalkability]
     * gets the tile walkability data(not necessarily what's saved)
     * @return HashMap String - Boolean representing whether each tile is walkable
     */
    static public HashMap<String, Boolean> getTileWalkability() {
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
        GameIO.abilityUnlocks = abilityUnlocks;
    }

    /**
     * [setLevelCompletion]
     * sets the (in-memory) stored level completion
     * use a write method to save it to an internal file
     * @param levelCompletion a Hashmap representing finished/unfinished levels
     */
    public static void setLevelCompletion(HashMap<String, Boolean> levelCompletion) {
        GameIO.levelCompletion = levelCompletion;
    }

    /**
     * [setEquipUnlocks]
     * sets the (in-memory) stored equip unlocks
     * use a write method to save it to an internal file
     * @param equipUnlocks a Hashmap representing locked/unlocked equips
     */
    public static void setEquipUnlocks(HashMap<String, Boolean> equipUnlocks) {
        GameIO.equipUnlocks = equipUnlocks;
    }

    /**
     * [setInventory]
     * sets the (in-memory) inventory
     * use a write method to save it to an internal file
     * @param inventory a Hashmap representing items and a count of each
     */
    public static void setInventory(HashMap<String, Integer> inventory) {
        GameIO.inventory = inventory;
    }

    /**
     * [setTimeState]
     * sets the (in-memory) time state
     * @param period the current ingame period
     * @param day the current ingame day
     */
    static public void setTimeState(int period, int day) {
        currentPeriod = period;
        currentDay = day;
    }


    //Dynamic file writing

    /**
     * [writeTimeState]
     * writes the in game time and period to an internal file
     */
    static public void writeTimeState() {
        writeFile("progression/time_state.txt", String.format("current_period %d\ncurrent_day %d", currentPeriod, currentDay));
    }

    /**
     * [writeInventory]
     * writes the dynamic inventory to the internal inventory file
     */
    static public void writeInventory() {
        writeHashMapToFile("inventory.txt", inventory);
    }

    /**
     * [writeLevelCompletion]
     * writes the dynamic level completion to an internal file
     */
    static public void writeLevelCompletion() {
        writeHashMapToFile("progression/level_completion.txt", levelCompletion);
    }

    /**
     * [writeEquipUnlocks]
     * writes the dynamic equipment unlocks to an internal file
     */
    static public void writeEquipUnlocks() {
        writeHashMapToFile("progression/equip_unlock.txt", equipUnlocks);
    }

    /**
     * [writeAbilityUnlocks]
     * writes the dynamic ability unlocks to an internal file
     */
    static public void writeAbilityUnlocks() {
        writeHashMapToFile("progression/ability_unlock.txt", abilityUnlocks);
    }



    /**
     * [getBattleLayout]
     * Gets the saved character layout on a 3x3 grid for battles from a file.
     * @return String[][] the 3x3 arrangement of characters
     */
    static public String[][] getBattleLayout() {
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
    static public void setBattleLayout(String[][] grid) {
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






    //Map based methods

    /**
     * [getMap]
     * Gets a 2d array of tiles from a specified map file.
     * WARNING: readTileWalkability must be called first in order to establish walkabilities of each given tile.
     * @param path the path of the map file (not including source folder)
     * @return OverworldTile[][] a 2d array of form [x][y] of Overworld tiles
     */
    static public OverworldTile[][] getMap(String path) {
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
                tileMap[x][y] = new OverworldTile(x, y, tileWalkability.get(tokens[x]));
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
    static public String[][] getMapAsString(String path) {
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
    static private String readFile(String path){
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
    static private void writeFile(String path, String s) {
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
    static private HashMap<String, Boolean> readFileAsHashMap(String path) {
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
    static private void writeHashMapToFile(String path, HashMap<?, ?> map) {
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
}

import utils.AnimatedSprite;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * [GameIO.java]
 * class that manages IO with text files for progression and saved data
 * @version 1.7
 * @author Allen Liu
 * @since June 10, 2019
 */
public class GameIO {

    /**
     * The string of the folder that all text files are to be placed in,
     * acting as a universal accessor
     */
    private static final String TEXT_SRC = "data/";

    private HashMap<String, Boolean> tileWalkability;

    //Dynamic game data
    private HashMap<String, Boolean> abilityUnlocks;

    private HashMap<String, Boolean> levelCompletion;
    private HashMap<String, Boolean> equipUnlocks;
    private HashMap<String, Integer> inventory;

    private int currentDay;
    private int currentPeriod;

    //Game initialization (file reading) methods
    public GameIO() {
        readInventory();
        readLevelCompletion();
        readEquipUnlocks();
        readAbilityUnlocks();
        readTimeState();
    }

    /**
     * [readTileWalkability]
     * loads all tile types, and saves whether they can be walked on or not
     * @param path the file path of the text file with all walkability values, not including source folder
     */
    public void readTileWalkability(String path) {
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
    public void readTimeState() {
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
    public void readLevelCompletion() {
        levelCompletion = readFileAsHashMap("progression/level_completion.txt");
    }

    /**
     * [readAbilityUnlocks]
     * reads the currently unlocked abilities - used to find whether a character can use one or not
     */
    public void readAbilityUnlocks() {
        abilityUnlocks = readFileAsHashMap("progression/ability_unlock.txt");
    }

    /**
     * [readEquipUnlocks]
     * reads the currently unlocked equips
     */
    public void readEquipUnlocks() {
        equipUnlocks = readFileAsHashMap("progression/equip_unlock.txt");
    }

    /**
     * [readInventory]
     * gets the saved inventory from the inventory file
     * @return HashMap (String, Integer) containing a list of items in the inventory, along with the count of each item
     */
    public void readInventory() {
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
    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    /**
     * [getTileWalkability]
     * gets the tile walkability data(not necessarily what's saved)
     * @return HashMap String - Boolean representing whether each tile is walkable
     */
    public HashMap<String, Boolean> getTileWalkability() {
        return tileWalkability;
    }

    /**
     * [getAbilityUnlocks]
     * gets the list of unlocked abilities
     * @return HashMap String - Boolean representing whether an ability is unlocked or not
     */
    public HashMap<String, Boolean> getAbilityUnlocks() {
        return abilityUnlocks;
    }

    /**
     * [getLevelCompletion]
     * gets the list of completed levels
     * @return HashMap String - Boolean representing whether a level is completed or not
     */
    public HashMap<String, Boolean> getLevelCompletion() {
        return levelCompletion;
    }

    /**
     * [getEquipUnlocks]
     * gets the list of unlocked equipment
     * @return HashMap String - Boolean representing whether an equip is unlocked or not
     */
    public HashMap<String, Boolean> getEquipUnlocks() {
        return equipUnlocks;
    }

    /**
     * [getCurrentDay]
     * gets the current day in game
     * @return int, the current day
     */
    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * [getCurrentPeriod]
     * gets the current period in game
     * @return int, the current period
     */
    public int getCurrentPeriod() {
        return currentPeriod;
    }


    //Dynamic setters

    /**
     * [setAbilityUnlocks]
     * sets the (in-memory) stored abilities
     * use a write method to save it to an internal file
     * @param abilityUnlocks a Hashmap representing locked/unlocked abilities
     */
    public void setAbilityUnlocks(HashMap<String, Boolean> abilityUnlocks) {
        this.abilityUnlocks = abilityUnlocks;
    }

    /**
     * [setLevelCompletion]
     * sets the (in-memory) stored level completion
     * use a write method to save it to an internal file
     * @param levelCompletion a Hashmap representing finished/unfinished levels
     */
    public void setLevelCompletion(HashMap<String, Boolean> levelCompletion) {
        this.levelCompletion = levelCompletion;
    }

    /**
     * [setEquipUnlocks]
     * sets the (in-memory) stored equip unlocks
     * use a write method to save it to an internal file
     * @param equipUnlocks a Hashmap representing locked/unlocked equips
     */
    public void setEquipUnlocks(HashMap<String, Boolean> equipUnlocks) {
        this.equipUnlocks = equipUnlocks;
    }

    /**
     * [setInventory]
     * sets the (in-memory) inventory
     * use a write method to save it to an internal file
     * @param inventory a Hashmap representing items and a count of each
     */
    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * [setTimeState]
     * sets the (in-memory) time state
     * @param period the current ingame period
     * @param day the current ingame day
     */
    public void setTimeState(int period, int day) {
        currentPeriod = period;
        currentDay = day;
    }


    //Dynamic file writing

    /**
     * [writeTimeState]
     * writes the in game time and period to an internal file
     */
    public void writeTimeState() {
        writeFile("progression/time_state.txt", String.format("current_period %d\ncurrent_day %d", currentPeriod, currentDay));
    }

    /**
     * [writeInventory]
     * writes the dynamic inventory to the internal inventory file
     */
    public void writeInventory() {
        writeHashMapToFile("inventory.txt", inventory);
    }

    /**
     * [writeLevelCompletion]
     * writes the dynamic level completion to an internal file
     */
    public void writeLevelCompletion() {
        writeHashMapToFile("progression/level_completion.txt", levelCompletion);
    }

    /**
     * [writeEquipUnlocks]
     * writes the dynamic equipment unlocks to an internal file
     */
    public void writeEquipUnlocks() {
        writeHashMapToFile("progression/equip_unlock.txt", equipUnlocks);
    }

    /**
     * [writeAbilityUnlocks]
     * writes the dynamic ability unlocks to an internal file
     */
    public void writeAbilityUnlocks() {
        writeHashMapToFile("progression/ability_unlock.txt", abilityUnlocks);
    }



    //Battle Layouts (for levels)

    /**
     * [getBattleLayout]
     * Gets the saved character layout on a 3x3 grid for battles from a file.
     * @return Player[] the list of players, including their positions
     */
    public static Player[] getBattleLayout() {
        String text = readFile("battle_layout.txt");
        String[] lines = text.split("\n");

        int playerNumber = 0;
        Player[] layout = new Player[3];

        for (int j = 0; j < 3; ++j) {
            String[] row = lines[j].split(" ");

            for (int i = 0; i < 3; ++i) {
                String tileData = row[i];
                if (!tileData.equals("*")) {
                    layout[playerNumber] = generatePlayer(tileData);
                    layout[playerNumber].setXGrid(i);
                    layout[playerNumber].setYGrid(j);
                    playerNumber++;
                }
            }
        }

        return layout;
    }

    /**
     * [setBattleLayout]
     * Saves a 3x3 battle layout for later use. The grid is expected to have only 3 characters but this is not strictly enforced.
     * @param grid the 3x3 grid of players to save
     */
    public static void setBattleLayout(Player[][] grid) {
        String toWrite = "";
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                if (grid[x][y] != null) {
                    toWrite += grid[x][y].getDebugName();
                } else {
                    toWrite += "*";
                }
                //Avoid trailing whitespace
                if (x < 2) {
                    toWrite += " ";
                }
            }
            //Avoid trailing newline
            if (y < 2) {
                toWrite += "\n";
            }
        }

        writeFile("battle_layout.txt", toWrite);
        //System.out.println(toWrite);
    }

    //Player file reading

    /**
     * [generatePlayer]
     * generates a player character from a file
     * @param debugName the debug name of the player to generate, should be equivalent to the player file name minus extension
     * @return Player, the player with attributes specified by the provided file
     */
    public static Player generatePlayer(String debugName) {
        String allText = readFile("players/" + debugName+ ".txt");
        String[] lines = allText.split("\n");

        String name = replaceFirstWord(lines[0]);

        AnimatedSprite animatedSprite = null;

        animatedSprite = generateAnimation(lines[1]);

        double health = Double.parseDouble(replaceFirstWord(lines[2]));
        double attack = Double.parseDouble(replaceFirstWord(lines[3]));
        double defence = Double.parseDouble(replaceFirstWord(lines[4]));
        double energy = Double.parseDouble(replaceFirstWord(lines[5]));

        int numAbilities = Integer.parseInt(replaceFirstWord(lines[6]));

        Ability[] abilities = new Ability[numAbilities];
        int lineNumber = 7;

        for (int i = 0; i < numAbilities; ++i) {
            abilities[i] = generateAbility(lines[lineNumber + i]);
        }

        return new Player(health, attack, defence, energy, debugName, name, animatedSprite, abilities);
    }

    private static AnimatedSprite generateAnimation(String line) throws ArrayIndexOutOfBoundsException {
        //Get the index of the next space or string 'breaker'
        int breakIndex = line.indexOf(" ");

        //rows, columns, width, height, delay

        //First word is ability type
        String path = line.substring(0, breakIndex);

        line = line.substring(line.indexOf("png") + 4);

        //Split the rest into args, hope that the length is equal to the amount required
        String[] args = line.split(" ");

        return new AnimatedSprite(path,
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2]));
    }
    /**
     * [generateAbility]
     * generates an ability based on a string defining its type, and the valid parameters
     * @param line the string that contains the ability type and arguments
     * @return Ability, an ability defined by a type, and a set of arguments
     * @throws ArrayIndexOutOfBoundsException when the list of args for a given ability is less than expected
     */
    private static Ability generateAbility(String line) throws ArrayIndexOutOfBoundsException {
        //Get the index of the next space or string 'breaker'
        int breakIndex = line.indexOf(" ");

        //First word is ability type
        String abilityType = line.substring(0, breakIndex);

        //Skip the space and quote = 2 characters to skip from the original space index
        breakIndex += 2;

        //Parse quoted text since it has spaces
        String name = line.substring(breakIndex, line.indexOf("\"", breakIndex));
        //Skip quote, space, and then next quote = 3 spaces
        line = line.substring(line.indexOf("\"", breakIndex) + 3);

        //Same as before, one other set of quoted text to parse
        //We skip the starting quote already
        String desc = line.substring(0, line.indexOf("\""));
        //Skip the quote and space to get to args = 2 spaces
        line = line.substring(line.indexOf("\"") + 2);

        //Split the rest into args, hope that the length is equal to the amount required
        String[] args = line.split(" ");

        //I hate myself so much
        switch (abilityType) {
            case "SingleAbility":
                return new SingleAbility(
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Double.parseDouble(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7]),
                        Double.parseDouble(args[8]),
                        Double.parseDouble(args[9]),
                        Boolean.parseBoolean(args[10]),
                        Boolean.parseBoolean(args[11]));
            case "BasicMoveAbility":
                return new BasicMoveAbility(name, desc,
                        Double.parseDouble(args[0]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]));
            case "AOEAbility":
                return new AOEAbility(
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Double.parseDouble(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7]),
                        Integer.parseInt(args[8]),
                        Integer.parseInt(args[9]),
                        Double.parseDouble(args[10]),
                        Double.parseDouble(args[11]),
                        Boolean.parseBoolean(args[12]),
                        Boolean.parseBoolean(args[13]));
            case "CombinationAbility":
                return new CombinationAbility(
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Double.parseDouble(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7]),
                        Integer.parseInt(args[8]),
                        Double.parseDouble(args[9]),
                        Boolean.parseBoolean(args[10]),
                        Boolean.parseBoolean(args[11]));
            case "StarAbility":
                return new StarAbility(
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Double.parseDouble(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7]),
                        Double.parseDouble(args[8]),
                        Double.parseDouble(args[9]),
                        Boolean.parseBoolean(args[10]),
                        Boolean.parseBoolean(args[11]));
            case "SpearAbility":
                return new SpearAbility(
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Integer.parseInt(args[4]),
                        Integer.parseInt(args[5]),
                        Double.parseDouble(args[6]),
                        Double.parseDouble(args[7]));
            case "StatusAbility":
                return new StatusAbility(
                        null,
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Integer.parseInt(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7]),
                        Boolean.parseBoolean(args[8]),
                        Boolean.parseBoolean(args[9])
                );
            case "AbilityPair":
                //Use ~~~ as separator
                int ability1StartIndex = line.indexOf("~~~") + 3;
                int ability1EndIndex = line.indexOf("~~~", ability1StartIndex);
                int ability2StartIndex = ability1EndIndex + 3;
                return new AbilityPair(
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Integer.parseInt(args[4]),
                        Integer.parseInt(args[5]),
                        generateAbility(line.substring(ability1StartIndex, ability1EndIndex)),
                        generateAbility(line.substring(ability2StartIndex))
                );
            case "LureAbility":
                return new LureAbility(
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Double.parseDouble(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7]),
                        Double.parseDouble(args[8]),
                        Double.parseDouble(args[9])
                );
            case "EnergyAbility":
                return new EnergyAbility(
                        new AnimatedSprite(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])),
                        name, desc,
                        Double.parseDouble(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7]),
                        Double.parseDouble(args[8]),
                        Double.parseDouble(args[9])
                );
            default:
                return null;
        }
    }

    /**
     * [setPlayer]
     * sets the player data in a specified file
     * THIS METHOD IS NOT COMPLETE, WILL BE UPDATED WHEN PLAYER CLASS IS COMPLETE
     * @param path the file to write to, not including source folder
     * @param data the character data as a string **NOTE: THIS WILL CHANGE SOON**
     */
    public void setPlayer(String path, String data) {
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
    public OverworldTile[][] getMap(String path, int tileSize) {
        String mapText = readFile(path);
        OverworldTile[][] tileMap;
        int doorCounter = 0;
        String[] doorData;

        String[] lines = mapText.split("\n");

        String[] tokens = lines[0].split(" ");
        int width = Integer.parseInt(tokens[0]);
        int height = Integer.parseInt(tokens[1]);
        tileMap = new OverworldTile[width][height];

        for (int y = 0; y < height; ++y) {
            tokens = lines[y + 1].split(" ");
            for (int x = 0; x < width; ++x) {
                if (tokens[x].equals("door")) {
                    doorData = lines[height + 1 + doorCounter].split(" ");
                    tileMap[x][y] = new Door(x, y, tileWalkability.get("door"), tileSize, "door",
                            doorData[0], Integer.parseInt(doorData[1]), Integer.parseInt(doorData[2]));
                    ++doorCounter;
                } else {
                    tileMap[x][y] = new OverworldTile(x, y, tileWalkability.get(tokens[x]), tileSize, tokens[x]);
                }
            }
        }

        return tileMap;
    }

    /**
     * [getNPCs]
     * Gets an array of NPCs from a text file.
     * @param path the path of the map file (not including source folder)
     * @return OverworldNPC[] an array of the NPCs on the current map
     */
    public OverworldNPC[] getNPCs(String path) {
        String npcText = readFile(path);
        OverworldNPC[] npcs;

        int x, y;
        String name, message;
        String[] tokens;
        int counter = 0;

        String[] lines = npcText.split("\n");
        int totalNPCs = Integer.parseInt(lines[0]);
        npcs = new OverworldNPC[totalNPCs];

        for (int i = 1; i < totalNPCs * 2 + 1; ++i) {
            tokens = lines[i].split(" ");
            x = Integer.parseInt(tokens[0]);
            y = Integer.parseInt(tokens[1]);
            name = tokens[2];
            ++i;
            message = lines[i];
            npcs[counter] = new OverworldNPC(x, y, name, message);
            ++counter;
            }
        return npcs;
        }

    /**
     * [getObjects]
     * Gets an array of objects from a text file.
     * @param path the path of the map file (not including source folder)
     * @return OverworldObject[] an array of the objects on the current map
     */
    public OverworldObject[] getObjects(String path) {
        String text = readFile(path);
        OverworldObject[] objects;
        int x, y, radius, respawnX, respawnY;

        String[] lines = text.split("\n");

        String[] tokens = lines[0].split(" ");
        int totalObjects = Integer.parseInt(tokens[0]);
        objects = new OverworldObject[totalObjects];

        for (int i = 0; i < totalObjects; ++i) {
            tokens = lines[i + 1].split(" ");
            if (tokens[0].equals("orbiter")) {
                x = Integer.parseInt(tokens[1]);
                y = Integer.parseInt(tokens[2]);
                radius = Integer.parseInt(tokens[3]);
                respawnX = Integer.parseInt(tokens[4]);
                respawnY = Integer.parseInt(tokens[5]);
                objects[i] = new Orbiter(x, y, x - radius, y, respawnX, respawnY);
            }
        }
        return objects;
    }

    /**
     * [getMapAsString]
     * Gets a 2d array of strings representing tiles from a specified map tile
     * NOTE: readTileWalkability need not be called to get the tile names.
     * @param path the path of the map file (not including source folder)
     * @return String[][], the array of map tiles as strings
     */
    public String[][] getMapAsString(String path) {
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
     * [replaceFirstWord]
     * returns a string with the first word removed
     * @param s the string to get the words from
     * @return String, the input string with the first word (everything up to the first space) removed
     */
    private static String replaceFirstWord(String s) {
        return s.substring(s.indexOf(" ") + 1);
    }
}

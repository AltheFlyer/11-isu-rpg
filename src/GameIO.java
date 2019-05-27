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

    /**
     * [setTileWalkability]
     * loads all tile types, and saves whether they can be walked on or not
     * @param path the file path of the text file with all walkability values, not including source folder
     */
    static public void setTileWalkability(String path) {
        tileWalkability = new HashMap<String, Boolean>();
        String allData = readFile(path);

        String[] lines = allData.split("\n");

        for (int i = 0; i < lines.length; ++i) {
            int spaceIndex = lines[i].indexOf(" ");
            tileWalkability.put(lines[i].substring(0, spaceIndex), Boolean.valueOf(lines[i].substring(spaceIndex + 1)));
        }
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

            String row = text.substring(0, lineBreak);
            text = text.substring(lineBreak + 1);

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

    /**
     * [getInventory]
     * gets the saved inventory from the inventory file
     * @return HashMap (String, Integer) containing a list of items in the inventory, along with the count of each item
     */
    static public HashMap<String, Integer> getInventory() {
        String fullText = readFile("inventory.txt");
        HashMap<String, Integer> inventory = new HashMap<>();

        String[] lines = fullText.split("\n");
        for (int i = 0; i < lines.length; ++i) {
            int spacer = lines[i].indexOf(" ");
            inventory.put(lines[i].substring(0, spacer), Integer.parseInt(lines[i].substring(spacer + 1)));
        }

        return inventory;
    }

    /**
     * [setInventory]
     * @param inventory the inventory of the party, formatted as the list of items in inventory, and count of each item
     */
    static public void setInventory(HashMap<String, Integer> inventory) {
        ArrayList<String> items = new ArrayList<>(inventory.keySet());
        ArrayList<Integer> count = new ArrayList<>(inventory.values());

        String toWrite = "";

        for (int i = 0; i < inventory.size(); ++i) {
            toWrite += items.get(i) + " " + count.get(i);
            if (i != inventory.size() - 1) {
                toWrite += "\n";
            }
        }

        writeFile("inventory.txt", toWrite);
    }

    /**
     * [getMap]
     * Gets a 2d array of tiles from a specified map file.
     * WARNING: setTileWalkability must be called first in order to establish walkabilities of each given tile.
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
     * NOTE: setTileWalkability need not be called to get the tile names.
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
                text += input.readLine() + "\n";
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
}

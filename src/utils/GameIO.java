package utils;

import java.io.*;
import java.util.*;

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

    /**
     * [getBattleLayout]
     * gets the saved character layout on a 3x3 grid for battles
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
     * saves a battle layout configuration for later use
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
        Iterator<String> items = inventory.keySet().iterator();
        Iterator<Integer> count = inventory.values().iterator();

        String toWrite = "";

        for (int i = 0; i < inventory.size(); ++i) {
            toWrite += items.next() + " " + count.next();
            if (i != inventory.size() - 1) {
                toWrite += "\n";
            }
        }

        writeFile("inventory.txt", toWrite);
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

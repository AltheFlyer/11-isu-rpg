package experiment;

import javax.swing.*;
import java.util.Scanner;

public class Default {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        JFrame frame = new JFrame();

        for (int i = 0; i < 10000; ++i) {
            System.out.println("A");
        }

        if(false) {
            System.out.println(1 + 1);
        } else {
            System.out.println(2 + 1);
        }

    }
}

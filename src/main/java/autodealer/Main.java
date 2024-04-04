package autodealer;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import Menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            try {
                MainMenu.displayMainMenu();
            } catch (SQLException | ParseException e) {
                System.out.println("Une erreur s'est produite : " + e.getMessage());
            }

            System.out.println("Voulez-vous revenir au menu principal ? (0: Non, 1: Oui)");
            System.out.print("Choix : ");
            choice = scanner.nextInt();
        } while (choice != 0);

        System.out.println("Au revoir !");
        scanner.close();
    }
}
package Menu;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import GetFileInfo.*;

public class MainMenu {
    public static void displayMainMenu() throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu principal :");
            System.out.println("1. Récupérer et afficher les véhicules du fichier de données data.txt");
            System.out.println("2. Filtrer et afficher les véhicules de moins de 20 ans du fichier data.txt");
            System.out.println("3. Insérer les véhicules de moins de 20 ans dans la base de données");
            System.out.println("4. Afficher un menu permettant d’effectuer les opérations suivantes sur la base de données");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        ListDataFromFile.readAndDisplayFileContent();
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        ListRecentCarFromFile.displayRecentCars();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        AddRecentCarInDB.insertRecentCarsIntoDB();
                    } catch (Exception e) {
                        System.out.println("Erreur lors de l'insertion des véhicules récents dans la base de données : " + e.getMessage());
                    }
                    break;
                case 4:
                    CarManagerMenu.displayCarManagerMenu();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 0);

        scanner.close();
    }
}

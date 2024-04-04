package Menu;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import Controller.CarController;

public class CarManagerMenu {
    public static void displayCarManagerMenu() throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu Gestionnaire de Voitures :");
            System.out.println("1. Afficher les véhicules");
            System.out.println("2. Modifier un véhicule");
            System.out.println("3. Ajouter un véhicule");
            System.out.println("4. Supprimer un véhicule");
            System.out.println("5. Afficher les véhicules - Filtrer par un âge donné");
            System.out.println("6. Afficher les véhicules - Filtrer par un prix donné");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                	MainMenu.displayMainMenu();
                    break;
                case 1:
                    CarController.list();
                    break;
                case 2:
                	CarController.update();
                    break;
                case 3:
                    CarController.add();
                    break;
                case 4:
                	CarController.delete();
                    break;
                case 5:
                	CarController.listByAge();
                    break;
                case 6:
                	CarController.listByPrice();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 0);

        scanner.close();
    }
}


package Controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Dto.CarDto;
import Logger.Logger;
import Repository.CarRepository;

public class CarController {

    private static CarRepository carRepository = new CarRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void list() throws ParseException {
        System.out.println("Liste des véhicules :");
        List<CarDto> cars;
        try {
            cars = carRepository.findAll();
            if (cars.isEmpty()) {
                System.out.println("Aucun véhicule trouvé.");
            } else {
                for (CarDto car : cars) {
                    System.out.println("Immatriculation : " + car.getRegistrationNumber());
                    System.out.println("Marque : " + car.getBrand());
                    System.out.println("Modèle : " + car.getModel());
                    System.out.println("Âge : " + car.getAge() + " ans");
                    System.out.println("Prix : " + car.getPrice() + " €");
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des véhicules depuis la base de données : " + e.getMessage());
        }
    }

    public static void update() throws ParseException {
        System.out.println("Mise à jour d'un véhicule :");

        try {
            List<CarDto> cars = carRepository.findAll();
            if (cars.isEmpty()) {
                System.out.println("Aucun véhicule trouvé.");
                return;
            }

            System.out.println("Veuillez sélectionner le véhicule à mettre à jour :");
            for (int i = 0; i < cars.size(); i++) {
                CarDto car = cars.get(i);
                System.out.println((i + 1) + ". " + car.getBrand() + " " + car.getModel() + " (ID: " + car.getRegistrationNumber() + ")");
            }

            System.out.print("Choix : ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > cars.size()) {
                System.out.println("Choix invalide.");
                return;
            }

            CarDto selectedCar = cars.get(choice - 1);

            System.out.println("Entrez les nouvelles valeurs pour le véhicule (laissez vide pour conserver la valeur actuelle) :");
            scanner.nextLine();

            System.out.print("Nouvelle marque : ");
            String newBrand = scanner.nextLine();
            if (!newBrand.isEmpty()) {
                selectedCar.setBrand(newBrand);
            }

            System.out.print("Nouveau modèle : ");
            String newModel = scanner.nextLine();
            if (!newModel.isEmpty()) {
                selectedCar.setModel(newModel);
            }

            System.out.print("Nouvelle date de première immatriculation (format yyyy-MM-dd) : ");
            String newRegistrationDateStr = scanner.nextLine();
            if (!newRegistrationDateStr.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date newRegistrationDate = dateFormat.parse(newRegistrationDateStr);
                    
                    selectedCar.setRegistrationDate(newRegistrationDate);
                } catch (ParseException e) {
                    System.out.println("Format de date invalide. Veuillez saisir une date au format yyyy-MM-dd.");
                }
            }

            System.out.print("Nouveau prix : ");
            String newPriceStr = scanner.nextLine();
            if (!newPriceStr.isEmpty()) {
                int newPrice = Integer.parseInt(newPriceStr);
                selectedCar.setPrice(newPrice);
            }

            carRepository.update(selectedCar);
            Logger.logUpdate(
                    "car", String.format("registrationNumber=%s, brand=%s, model=%s, registrationDate=%s, price=%s",
                        selectedCar.getRegistrationNumber(), selectedCar.getBrand(), selectedCar.getModel(), selectedCar.getRegistrationDate(), selectedCar.getPrice())
                );
            System.out.println("Le véhicule a été mis à jour avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du véhicule : " + e.getMessage());
        }
    }
    
    public static void add() throws SQLException {
        System.out.println("Ajout d'un nouveau véhicule :");

        System.out.print("Numero de registre : ");
        String id = scanner.nextLine();
        
        System.out.print("Marque : ");
        String brand = scanner.nextLine();

        System.out.print("Modèle : ");
        String model = scanner.nextLine();

        System.out.print("Date de première immatriculation (format yyyy-MM-dd) : ");
        String registrationDateStr = scanner.nextLine();
        Date registrationDate = null;
        try {
            registrationDate = new SimpleDateFormat("yyyy-MM-dd").parse(registrationDateStr);
        } catch (ParseException e) {
            System.out.println("Format de date incorrect. Veuillez saisir une date au format yyyy-MM-dd.");
            return;
        }

        System.out.print("Prix : ");
        int price = scanner.nextInt();
        scanner.nextLine();

        CarDto carDto = new CarDto(id, brand, model, registrationDate, price, null);

        try {
            carRepository.add(carDto);
            Logger.logAdd(
            		"car", String.format("registrationNumber=%s, brand=%s, model=%s, registrationDate=%s, price=%s",
            				carDto.getRegistrationNumber(), carDto.getBrand(), carDto.getModel(), carDto.getRegistrationDate(), carDto.getPrice())
            		);
            System.out.println("Le véhicule a été enregistré avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du véhicule dans la base de données : " + e.getMessage());
        }
    }
    
    public static void delete() throws ParseException {
        System.out.println("Suppression d'un véhicule :");

        try {
            List<CarDto> cars = carRepository.findAll();
            if (cars.isEmpty()) {
                System.out.println("Aucun véhicule trouvé.");
                return;
            }

            System.out.println("Veuillez sélectionner le véhicule à supprimer :");
            for (int i = 0; i < cars.size(); i++) {
                CarDto car = cars.get(i);
                System.out.println((i + 1) + ". " + car.getBrand() + " " + car.getModel() + " (ID: " + car.getRegistrationNumber() + ")");
            }

            System.out.print("Choix : ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > cars.size()) {
                System.out.println("Choix invalide.");
                return;
            }

            CarDto selectedCar = cars.get(choice - 1);

            carRepository.delete(selectedCar.getRegistrationNumber());
            Logger.logDelete(
                "car", String.format("registrationNumber=%s, brand=%s, model=%s, registrationDate=%s, price=%s",
                    selectedCar.getRegistrationNumber(), selectedCar.getBrand(), selectedCar.getModel(), selectedCar.getRegistrationDate(), selectedCar.getPrice())
            );
            System.out.println("Le véhicule a été supprimé avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du véhicule : " + e.getMessage());
        }
    }


    public static List<CarDto> listByAge() throws SQLException, ParseException {
        List<CarDto> carsToDisplay = new ArrayList<>();
        List<CarDto> allCars = carRepository.findAll();
        
        System.out.print("Entrez l'âge maximum des véhicules à afficher : ");
        int ageThreshold = scanner.nextInt();
        		
        for (CarDto car : allCars) {
            if (car.getAge() == ageThreshold) {
                carsToDisplay.add(car);
            }
        }
        
        if (carsToDisplay.isEmpty()) {
            System.out.println("Aucun véhicule trouvé.");
        } else {
            System.out.println("Liste des véhicules ayant" + ageThreshold + " ans :");
            for (CarDto car : carsToDisplay) {
            	System.out.println("Immatriculation : " + car.getRegistrationNumber());
                System.out.println("Marque : " + car.getBrand());
                System.out.println("Modèle : " + car.getModel());
                System.out.println("Âge : " + car.getRegistrationDate());
                System.out.println("Prix : " + car.getPrice() + " €");
                System.out.println();
            }
        }

        return carsToDisplay;
    }

    public static List<CarDto> listByPrice() throws SQLException, ParseException {
        List<CarDto> carsToDisplay = new ArrayList<>();
        List<CarDto> allCars = carRepository.findAll();

        System.out.print("Entrez le prix minimum des véhicules à afficher : ");
        int minPrice = scanner.nextInt();
        System.out.print("Entrez le prix maximum des véhicules à afficher : ");
        int maxPrice = scanner.nextInt();
        			
        for (CarDto car : allCars) {
            int carPrice = car.getPrice();
            if (carPrice >= minPrice && carPrice <= maxPrice) {
                carsToDisplay.add(car);
            }
        }
        
        if (carsToDisplay.isEmpty()) {
            System.out.println("Aucun véhicule trouvé dans la fourchette de prix spécifiée.");
        } else {
            System.out.println("Liste des véhicules ayant un prix compris entre " + minPrice + " € et " + maxPrice + " € :");
            for (CarDto car : carsToDisplay) {
                System.out.println("Immatriculation : " + car.getRegistrationNumber());
                System.out.println("Marque : " + car.getBrand());
                System.out.println("Modèle : " + car.getModel());
                System.out.println("Âge : " + car.getRegistrationDate());
                System.out.println("Prix : " + car.getPrice() + " €");
                System.out.println();
            }
        }

        return carsToDisplay;
    }
}


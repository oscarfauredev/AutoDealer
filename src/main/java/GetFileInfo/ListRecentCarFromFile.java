package GetFileInfo;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class ListRecentCarFromFile {

    public static void setRecentCarInstance(GetRecentCar recentCar) {
    }

    public static void displayRecentCars() {
        try {
            List<String[]> recentCars = GetRecentCar.getRecentCars();

            for (String[] vehicleInfo : recentCars) {
                System.out.println("Registration Number: " + vehicleInfo[0]);
                System.out.println("Brand: " + vehicleInfo[1]);
                System.out.println("Model: " + vehicleInfo[2]);
                System.out.println("Date of First Registration: " + vehicleInfo[3]);
                System.out.println("Price: " + vehicleInfo[4]);
                System.out.println();
            }
        } catch (FileNotFoundException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}

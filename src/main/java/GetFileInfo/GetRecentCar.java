package GetFileInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GetRecentCar {
    public static List<String[]> getRecentCars() throws FileNotFoundException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        List<String[]> recentCars = new ArrayList<String[]>();

        try {
            File file = GetFile.getDataFile();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] vehicleInfo = line.split(",");
                Date registrationDate = dateFormat.parse(vehicleInfo[3]);
                long diffInMillies = Math.abs(currentDate.getTime() - registrationDate.getTime());
                long diff = diffInMillies / (24 * 60 * 60 * 1000);

                if (diff < 365 * 20) {
                    recentCars.add(vehicleInfo);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Le fichier data.txt n'a pas été trouvé dans le projet.");
        }

        return recentCars;
    }
}
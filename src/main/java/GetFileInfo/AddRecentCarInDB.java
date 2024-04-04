package GetFileInfo;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import autodealer.DBConfig;

public class AddRecentCarInDB {
    public static void insertRecentCarsIntoDB() throws FileNotFoundException, SQLException, ParseException {
        SimpleDateFormat sourceDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            List<String[]> recentCars = GetRecentCar.getRecentCars();
            Connection conn = DBConfig.getConnection();
            String insertQuery = "INSERT INTO car (registration_number, brand, model, date_of_first_registration, price) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement query = conn.prepareStatement(insertQuery);

            for (String[] vehicleInfo : recentCars) {
                String registrationDateStr = vehicleInfo[3];
                java.util.Date registrationDate = sourceDateFormat.parse(registrationDateStr);
                String formattedDate = targetDateFormat.format(registrationDate);

                query.setString(1, vehicleInfo[0]);
                query.setString(2, vehicleInfo[1]);
                query.setString(3, vehicleInfo[2]);
                query.setString(4, formattedDate);
                query.setString(5, vehicleInfo[4]);

                query.executeUpdate();
            }

            conn.close();
            System.out.println("Les véhicules récents ont été insérés dans la base de données avec succès.");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Le fichier data.txt n'a pas été trouvé dans le projet.");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'insertion des données dans la base de données : " + e.getMessage());
        }
    }
}
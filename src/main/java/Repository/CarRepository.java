package Repository;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Dto.CarDto;
import Factory.CarDtoFactory;
import autodealer.DBConfig;

public class CarRepository {

    public List<CarDto> findAll() throws SQLException, ParseException {
        List<CarDto> cars = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM car")) {

            while (rs.next()) {
                String registrationNumber = rs.getString("registration_number");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                Date registrationDate = rs.getDate("date_of_first_registration");
                int price = rs.getInt("price");

                CarDto carDto = CarDtoFactory.createCarDto(registrationNumber, brand, model, registrationDate, price);
                cars.add(carDto);
            }
        }
        return cars;
    }

    public List<CarDto> findBy(String column, String value) throws SQLException, ParseException {
        List<CarDto> cars = new ArrayList<>();
        String query = "SELECT * FROM car WHERE " + column + " = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, value);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String registrationNumber = rs.getString("registration_number");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                Date registrationDate = rs.getDate("date_of_first_registration");
                int price = rs.getInt("price");

                CarDto carDto = CarDtoFactory.createCarDto(registrationNumber, brand, model, registrationDate, price);
                cars.add(carDto);
            }
        }
        return cars;
    }

    public void add(CarDto carDto) throws SQLException {
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO car (registration_number, brand, model, date_of_first_registration, price) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setString(1, carDto.getRegistrationNumber());
            pstmt.setString(2, carDto.getBrand());
            pstmt.setString(3, carDto.getModel());
            pstmt.setDate(4, new java.sql.Date(carDto.getRegistrationDate().getTime()));
            pstmt.setInt(5, carDto.getPrice());

            pstmt.executeUpdate();
        }
    }
    
    public void update(CarDto carDto) throws SQLException {
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE car SET brand = ?, model = ?, date_of_first_registration = ?, price = ? WHERE registration_number = ?")) {

            pstmt.setString(1, carDto.getBrand());
            pstmt.setString(2, carDto.getModel());
            pstmt.setDate(3, new java.sql.Date(carDto.getRegistrationDate().getTime()));
            pstmt.setInt(4, carDto.getPrice());
            pstmt.setString(5, carDto.getRegistrationNumber());

            pstmt.executeUpdate();
        }
    }

    public void delete(String registrationNumber) throws SQLException {
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM car WHERE registration_number = ?")) {

            pstmt.setString(1, registrationNumber);

            pstmt.executeUpdate();
        }
    }
}

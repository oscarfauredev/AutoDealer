package Factory;

import java.text.ParseException;
import java.util.Date;

import Dto.CarDto;

public class CarDtoFactory {
    public static CarDto createCarDto(String registrationNumber, String brand, String model, Date registrationDate, int price) throws ParseException {
        int age = calculateAge(registrationDate);
        return new CarDto(registrationNumber, brand, model, registrationDate, price, age);
    }

    private static int calculateAge(Date registrationDate) {
        Date currentDate = new Date();

        long diffInMillies = Math.abs(currentDate.getTime() - registrationDate.getTime());
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
        int years = (int) (diffInDays / 365);
        return years;
    }
}


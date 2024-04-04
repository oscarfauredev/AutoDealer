package Dto;

import java.util.Date;

public class CarDto {
    private String registrationNumber;
    private String brand;
    private String model;
    private Date registrationDate;
    private int price;
    private Integer age;

    public CarDto
    	(
    	String registrationNumber, 
    	String brand, String model,
    	Date registrationDate,
    	int price,
    	Integer age
    		) {
	        this.registrationNumber = registrationNumber;
	        this.brand = brand;
	        this.model = model;
	        this.registrationDate = registrationDate;
	        this.price = price;
	        this.age = age;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


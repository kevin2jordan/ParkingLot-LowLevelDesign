package model.vehicle;

import model.enums.VehicleType;

public class Car extends Vehicle{
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }
}

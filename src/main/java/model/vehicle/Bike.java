package model.vehicle;

import model.enums.VehicleType;

public class Bike extends Vehicle{
    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleType.BIKE);
    }
}

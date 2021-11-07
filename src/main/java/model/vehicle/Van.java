package model.vehicle;

import model.enums.VehicleType;

public class Van extends Vehicle{
    public Van(String licenseNumber) {
        super(licenseNumber, VehicleType.VAN);
    }
}

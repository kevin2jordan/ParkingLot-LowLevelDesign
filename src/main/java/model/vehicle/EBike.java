package model.vehicle;

import model.enums.VehicleType;

public class EBike extends Vehicle{

    public EBike(String licenseNumber) {
        super(licenseNumber, VehicleType.EBIKE);
    }
}

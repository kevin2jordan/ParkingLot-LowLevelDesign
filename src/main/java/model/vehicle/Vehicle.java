package model.vehicle;

import lombok.Getter;
import lombok.Setter;
import model.ParkingTicket;
import model.enums.VehicleType;

@Getter
@Setter
public abstract class Vehicle {
    private String licenseNumber;
    private VehicleType vehicleType;
    private ParkingTicket parkingTicket;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }
}

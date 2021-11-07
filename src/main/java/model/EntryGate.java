package model;

import exception.ParkingSpaceNotAvailiableException;
import lombok.Getter;
import model.enums.TicketStatus;
import model.parkingspot.ParkingSpace;
import model.vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

@Getter
public class EntryGate {

    private String entryGateId;

    public EntryGate(String entryGateId) {
        this.entryGateId = entryGateId;
    }

    public ParkingTicket getParkingTicket(Vehicle vehicle) {
        if(!ParkingLot.INSTANCE.canPark(vehicle.getVehicleType())) {
            throw new ParkingSpaceNotAvailiableException("Parking space not availiable exception");
        }
        ParkingSpace parkingSpace = ParkingLot.INSTANCE.getParkingSpace(vehicle.getVehicleType());
        if (parkingSpace == null) {
            throw new ParkingSpaceNotAvailiableException("Parking space not availiable exception");
        }
        return buildTicket(parkingSpace.getParkingSpaceId(), vehicle.getLicenseNumber());
    }

    private ParkingTicket buildTicket(String parkingSpaceId, String licenseNumber) {

        return ParkingTicket.builder()
                .parkingTicketId(UUID.randomUUID().toString())
                .licenseNumber(licenseNumber)
                .parkingSpaceId(parkingSpaceId)
                .entryDate(new Date())
                .ticketStatus(TicketStatus.ACTIVE)
                .build();
    }
}

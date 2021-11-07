package model.parkingspot;

import lombok.Getter;
import lombok.Setter;
import model.enums.ParkingSpaceStatus;
import model.enums.ParkingSpaceType;

@Getter
@Setter
public class ParkingSpace {

    private String parkingSpaceId;
    private ParkingSpaceStatus parkingSpaceStatus;
    private ParkingSpaceType parkingSpaceType;
    private String vehicleId;

    public ParkingSpace(String parkingSpaceId, ParkingSpaceType parkingSpaceType ) {
        this.parkingSpaceId = parkingSpaceId;
        this.parkingSpaceType = parkingSpaceType;
    }

    public void assignVehicleToParkingSpace(String vehicleId) {
        this.vehicleId = vehicleId;
        this.parkingSpaceStatus = ParkingSpaceStatus.BOOKED;
    }

    public void freeParkingSpace() {
        this.vehicleId = null;
        this.parkingSpaceStatus = ParkingSpaceStatus.AVAILIABLE;
    }
}

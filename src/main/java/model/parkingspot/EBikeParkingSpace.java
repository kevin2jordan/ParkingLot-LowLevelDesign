package model.parkingspot;

import model.enums.ParkingSpaceType;

public class EBikeParkingSpace extends ParkingSpace{
    public EBikeParkingSpace(String parkingSpaceId) {
        super(parkingSpaceId, ParkingSpaceType.EBIKE);
    }
}

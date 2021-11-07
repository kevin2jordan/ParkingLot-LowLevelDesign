package model.parkingspot;

import model.enums.ParkingSpaceType;

public class AbledParkingSpace extends ParkingSpace{
    public AbledParkingSpace(String parkingSpaceId) {
        super(parkingSpaceId, ParkingSpaceType.ABLED);
    }
}

package model.parkingspot;

import model.enums.ParkingSpaceType;

public class BikeParkingSpace extends ParkingSpace{
    public BikeParkingSpace(String parkingSpaceId) {
        super(parkingSpaceId, ParkingSpaceType.BIKE);
    }
}

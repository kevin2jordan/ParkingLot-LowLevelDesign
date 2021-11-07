package model.parkingspot;

import model.enums.ParkingSpaceType;

public class CarParkingSpace extends ParkingSpace{
    public CarParkingSpace(String parkingSpaceId) {
        super(parkingSpaceId, ParkingSpaceType.CAR);
    }
}

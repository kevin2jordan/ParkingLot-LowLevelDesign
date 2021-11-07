package model;

import lombok.Getter;
import lombok.Setter;
import model.enums.ParkingSpaceType;
import model.enums.VehicleType;
import model.parkingspot.ParkingSpace;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ParkingFloor {

    @Getter
    @Setter
    private String parkingFloorId;

    @Getter
    private Map<ParkingSpaceType, Deque<ParkingSpace>> parkingSpaces = new HashMap<>();
    private Map<String, ParkingSpace> usedParkingSpace = new HashMap<>();

    public ParkingFloor(String parkingFloorId) {
        this.parkingFloorId = parkingFloorId;
        parkingSpaces.put(ParkingSpaceType.ABLED, new ConcurrentLinkedDeque());
        parkingSpaces.put(ParkingSpaceType.CAR, new ConcurrentLinkedDeque<>());
        parkingSpaces.put(ParkingSpaceType.BIKE, new ConcurrentLinkedDeque<>());
        parkingSpaces.put(ParkingSpaceType.EBIKE, new ConcurrentLinkedDeque<>());
        parkingSpaces.put(ParkingSpaceType.LARGE, new ConcurrentLinkedDeque<>());
    }

    public boolean isFloorFull() {

        BitSet bitSet = new BitSet();
        int index = 0;
        for(Deque<ParkingSpace> parkingSpace : parkingSpaces.values()) {
            bitSet.set(index++, parkingSpace.isEmpty());
        }
        return bitSet.size() == bitSet.cardinality();
    }

    public static ParkingSpaceType getParkingSpaceTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return ParkingSpaceType.CAR;
            case VAN:
                return ParkingSpaceType.LARGE;
            case BIKE:
                return ParkingSpaceType.BIKE;
            case EBIKE:
                return ParkingSpaceType.EBIKE;
            case TRUCK:
                return ParkingSpaceType.LARGE;
            default:
                return ParkingSpaceType.LARGE;
        }
    }

    public boolean canPark(VehicleType vehicleType) {
        return canPark(getParkingSpaceTypeForVehicle(vehicleType));
    }

    public boolean canPark(ParkingSpaceType parkingSpaceType) {
        return parkingSpaces.get(parkingSpaceType).size() > 0;
    }


    public synchronized ParkingSpace getParkingSpace( VehicleType vehicleType) {
        ParkingSpace parkingSpace = null;
        if(canPark(vehicleType)) {
            ParkingSpaceType parkingSpaceType =  getParkingSpaceTypeForVehicle(vehicleType);
            parkingSpace = parkingSpaces.get(parkingSpaceType).poll();
            assert parkingSpace != null;
            usedParkingSpace.put(parkingSpace.getParkingSpaceId(), parkingSpace);
        }

        return parkingSpace;
    }

    public ParkingSpace vacateParkingSpace(String parkingSpaceId) {
        ParkingSpace parkingSpace = usedParkingSpace.remove(parkingSpaceId);
        if(parkingSpace != null) {
            parkingSpace.freeParkingSpace();
            parkingSpaces.get(parkingSpace.getParkingSpaceType()).addFirst(parkingSpace);
        }

        return parkingSpace;
    }

}

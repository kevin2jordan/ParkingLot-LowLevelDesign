package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.enums.VehicleType;
import model.parkingspot.ParkingSpace;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ParkingLot {

    private String parkingLotId;
    private String parkingLotName;
    private Address address;

    private List<ParkingFloor> parkingFloors;
    private List<EntryGate> entryGates;
    private List<ExitGate> exitGates;

    public static ParkingLot INSTANCE = new ParkingLot();

    public ParkingLot() {
        this.parkingLotId = UUID.randomUUID().toString();
        parkingFloors = new LinkedList<>();
        entryGates = new LinkedList<>();
        exitGates = new LinkedList<>();
    }

    public boolean isFull() {
        int index = 0;
        BitSet bitSet = new BitSet();
        for(ParkingFloor parkingFloor : parkingFloors) {
            bitSet.set(index++, parkingFloor.isFloorFull());
        }
        return bitSet.cardinality() == bitSet.size();
    }

    public boolean canPark(@NonNull final VehicleType vehicleType) {
        for(ParkingFloor parkingFloor : INSTANCE.parkingFloors) {
            if(parkingFloor.canPark(vehicleType)){
                return true;
            }
        }
        return false;
    }

    public ParkingSpace getParkingSpace(@NonNull final VehicleType vehicleType) {
        for(ParkingFloor parkingFloor : parkingFloors) {
            ParkingSpace parkingSpace = parkingFloor.getParkingSpace(vehicleType);
            if(parkingSpace != null) {
                return parkingSpace;
            }
        }
        return null;
    }

    public ParkingSpace vacateParkingSpace(@NonNull final String parkingSpaceId) {
        for(ParkingFloor parkingFloor : parkingFloors) {
            ParkingSpace parkingSpace = parkingFloor.vacateParkingSpace(parkingSpaceId);
            if(parkingSpace != null) {
                return parkingSpace;
            }
        }
        return null;
    }
}

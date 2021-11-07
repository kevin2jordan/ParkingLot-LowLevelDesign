package service;

import exception.*;
import lombok.NonNull;
import model.EntryGate;
import model.ExitGate;
import model.ParkingFloor;
import model.ParkingLot;
import model.parkingspot.ParkingSpace;
import org.springframework.lang.NonNullFields;

import java.util.*;

public class ParkingLotService {

    private final Map<String, ParkingLot> parkingLots = new HashMap<>();

    public void addParkingLot(@NonNull final ParkingLot parkingLot) {
        if(parkingLots.containsKey(parkingLot.getParkingLotId())) {
            throw new ParkingLotAlreadyExistException("ParkingLot already exist");
        }
        parkingLots.put(parkingLot.getParkingLotId(), parkingLot);
    }

    public ParkingLot getParkingLotById(@NonNull final String parkingLotId) {
        if(!parkingLots.containsKey(parkingLotId)) {
            throw new ParkingLotNotFoundException("ParkingLot not found exception");
        }
        return parkingLots.get(parkingLotId);
    }

    public void addParkingFloorToParkingLot(@NonNull final String parkingLotId, @NonNull final ParkingFloor parkingFloor) {
        if (!parkingLots.containsKey(parkingLotId)) {
            throw new ParkingLotNotFoundException("ParkingLot not found exception");
        }
        ParkingLot parkingLot = parkingLots.get(parkingLotId);

        Optional<ParkingFloor> floor = parkingLot.getParkingFloors().stream()
                .filter(pFloor -> pFloor.getParkingFloorId().equalsIgnoreCase(parkingFloor.getParkingFloorId()))
                .findFirst();

        if (floor.isPresent()) {
            throw new ParkingFloorAlreadyExistException("Parking floor already exist");
        }

        parkingLot.getParkingFloors().add(parkingFloor);
    }

    public void addParkingSpaceToParkingFloorOfaParkingLot(@NonNull final String parkingLotId,
               @NonNull final String parkingFloorId, @NonNull final ParkingSpace parkingSpace) {

        if(!parkingLots.containsKey(parkingLotId)) {
            throw new ParkingLotNotFoundException("Parking lot not found exception");
        }
        ParkingLot parkingLot = parkingLots.get(parkingLotId);

        Optional<ParkingFloor> parkingFloor = parkingLot.getParkingFloors().stream()
                .filter(pfloor -> pfloor.getParkingFloorId().equalsIgnoreCase(parkingFloorId))
                .findFirst();

        if(parkingFloor.isEmpty()) {
            throw new InvalidParkingFloorException("Invalid parking floor");
        }
        ParkingFloor pFloor = parkingFloor.get();
        Optional<ParkingSpace> space = pFloor.getParkingSpaces().get(parkingSpace.getParkingSpaceType()).stream()
                .filter(pSpace -> pSpace.getParkingSpaceId().equalsIgnoreCase(parkingSpace.getParkingSpaceId()))
                .findFirst();

        if(space.isPresent()){
            throw  new ParkingSpaceAlreadyExist("Parking space already exist");
        }

        pFloor.getParkingSpaces().get(parkingSpace.getParkingSpaceType()).add(parkingSpace);
    }

    public void addEntryGateToAParkingLot(@NonNull final String parkingLotId, @NonNull final EntryGate entryGate) {
        if(!parkingLots.containsKey(parkingLotId)){
            throw new ParkingLotNotFoundException("Parking lot not found exception");
        }
        Optional<EntryGate> enGate = parkingLots.get(parkingLotId).getEntryGates()
                .stream()
                .filter(gate -> gate.getEntryGateId().equalsIgnoreCase(entryGate.getEntryGateId()))
                .findFirst();
        if(enGate.isPresent()){
            throw new GateAlreadyExistException("Entry gate already exist");
        }
        parkingLots.get(parkingLotId).getEntryGates().add(entryGate);
    }

    public void addExitGateToAParkingLot(@NonNull final String parkingLotId, @NonNull final ExitGate exitGate) {
        if(!parkingLots.containsKey(parkingLotId)){
            throw new ParkingLotNotFoundException("Parking lot not found exception");
        }
        Optional<ExitGate> enGate = parkingLots.get(parkingLotId).getExitGates()
                .stream()
                .filter(gate -> gate.getExitGateId().equalsIgnoreCase(exitGate.getExitGateId()))
                .findFirst();
        if(enGate.isPresent()){
            throw new GateAlreadyExistException("Entry gate already exist");
        }
        parkingLots.get(parkingLotId).getExitGates().add(exitGate);
    }
}

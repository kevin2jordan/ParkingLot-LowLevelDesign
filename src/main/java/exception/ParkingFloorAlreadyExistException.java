package exception;

public class ParkingFloorAlreadyExistException extends RuntimeException{
    public ParkingFloorAlreadyExistException(String message) {
        super(message);
    }
}

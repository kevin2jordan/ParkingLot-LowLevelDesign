package exception;

public class ParkingSpaceNotAvailiableException extends RuntimeException{
    public ParkingSpaceNotAvailiableException(String message) {
        super(message);
    }
}

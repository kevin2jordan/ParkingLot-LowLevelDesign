package exception;

public class InvalidParkingFloorException extends RuntimeException{
    public InvalidParkingFloorException(String message){
        super(message);
    }
}

package exception;

public class GateAlreadyExistException extends RuntimeException{

    public GateAlreadyExistException(String message) {
        super(message);
    }
}

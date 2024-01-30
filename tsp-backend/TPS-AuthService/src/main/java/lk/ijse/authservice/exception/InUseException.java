package lk.ijse.authservice.exception;


public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}

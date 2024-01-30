package lk.ijse.tps.bookingservice.exception;



public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}

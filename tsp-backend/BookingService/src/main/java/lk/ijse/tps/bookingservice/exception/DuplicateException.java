package lk.ijse.tps.bookingservice.exception;



public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}

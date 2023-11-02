package lk.ijse.hotelservicemysql.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}

package lk.ijse.userservicemysql.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}

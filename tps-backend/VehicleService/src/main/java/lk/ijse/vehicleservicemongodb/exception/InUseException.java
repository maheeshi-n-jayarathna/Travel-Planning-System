package lk.ijse.vehicleservicemongodb.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}

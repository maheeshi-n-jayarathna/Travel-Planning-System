package lk.ijse.bookingservice.handler;

import lk.ijse.bookingservice.exception.DuplicateException;
import lk.ijse.bookingservice.exception.InUseException;
import lk.ijse.bookingservice.exception.InvalidException;
import lk.ijse.bookingservice.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobeVideExceptionHandler {

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateException(Exception exception) {
        return ResponseEntity.badRequest().body(exception);
    }
    @ExceptionHandler(InUseException.class)
    public ResponseEntity<?> handleInUseException(Exception exception) {
        return ResponseEntity.badRequest().body(exception);
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInValidException(Exception exception) {
        return ResponseEntity.badRequest().body(exception);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFountException(Exception exception) {
        return ResponseEntity.badRequest().body(exception);
    }
}

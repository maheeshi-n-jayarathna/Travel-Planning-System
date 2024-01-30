package lk.ijse.tps.tps.guideservice.handler;

import lk.ijse.tps.tps.guideservice.exception.DuplicateException;
import lk.ijse.tps.tps.guideservice.exception.InUseException;
import lk.ijse.tps.tps.guideservice.exception.InvalidException;
import lk.ijse.tps.tps.guideservice.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobeVideExceptionHandler {

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateException(Exception exception) {
        return ResponseEntity.badRequest().body("Duplicate case\n "+exception.getMessage());
    }
    @ExceptionHandler(InUseException.class)
    public ResponseEntity<?> handleInUseException(Exception exception) {
        return ResponseEntity.badRequest().body("In use case\n "+exception.getMessage());
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInValidException(Exception exception) {
        return ResponseEntity.badRequest().body("In valid case\n "+exception.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFountException(Exception exception) {
        return ResponseEntity.badRequest().body("Not found case\n "+exception.getMessage());
    }
}

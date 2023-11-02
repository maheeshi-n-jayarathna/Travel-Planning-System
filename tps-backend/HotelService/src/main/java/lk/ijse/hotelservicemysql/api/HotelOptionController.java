package lk.ijse.hotelservicemysql.api;

import jakarta.validation.Valid;
import lk.ijse.hotelservicemysql.dto.HotelOptionDTO;
import lk.ijse.hotelservicemysql.exception.InvalidException;
import lk.ijse.hotelservicemysql.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hotelOption")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class HotelOptionController {
    private final HotelService hotelService;

    @GetMapping("{hotelOptionId}")
    ResponseEntity<?> getSelectedHotelOption(@PathVariable String hotelOptionId) {
        return ResponseEntity.ok(hotelService.getSelectedHotelOption(hotelOptionId));
    }

    @GetMapping
    ResponseEntity<?> getAllHotelOption() {
        return ResponseEntity.ok(hotelService.getAllHotelOption());
    }

    @PostMapping
    ResponseEntity<?> saveHotelOption(@Valid @RequestBody HotelOptionDTO hotelOptionDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
        return ResponseEntity.ok(hotelService.addHotelOption(hotelOptionDTO));
    }

    @PutMapping("{hotelOptionId}")
    ResponseEntity<?> updateHotelOption(@PathVariable String hotelOptionId, @Valid @RequestBody HotelOptionDTO hotelOptionDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new InvalidException(errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList().toString()
            );
        }
        hotelOptionDTO.setHotelOptionId(hotelOptionId);
        hotelService.updateHotelOption(hotelOptionDTO);
        return ResponseEntity.ok("Hotel option updated");
    }

    @DeleteMapping("{hotelOptionId}")
    ResponseEntity<?> deleteHotelOption(@PathVariable String hotelOptionId) {
        hotelService.deleteHotelOption(hotelOptionId);
        return ResponseEntity.ok("Hotel option deleted");
    }
}

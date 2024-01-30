package lk.ijse.authservice.dto.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleBookingDTO {
    private String bookingId;
    private String vehicleId;
    private double distance;
    private BigDecimal pricePerKM;
    private int rate;
}

package lk.ijse.tps.bookingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleBookingPk implements Serializable {
    private String bookingId;
    private String vehicleId;
}

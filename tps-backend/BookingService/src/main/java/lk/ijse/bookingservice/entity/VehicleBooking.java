package lk.ijse.bookingservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(lk.ijse.bookingservice.entity.VehicleBookingPk.class)
public class VehicleBooking {
    @Id
    private String bookingId;
    @Id
    private String vehicleId;

    private double distance;
    private BigDecimal pricePerKM;
    private int rate;

    @ManyToOne
    @JoinColumn(name = "bookingId",referencedColumnName = "bookingId",insertable = false,updatable = false)
    private Booking booking;
}

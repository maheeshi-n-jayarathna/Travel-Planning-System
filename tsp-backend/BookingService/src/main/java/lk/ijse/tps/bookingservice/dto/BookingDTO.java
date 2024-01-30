package lk.ijse.tps.bookingservice.dto;


import lk.ijse.tps.bookingservice.dto.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO {
    private String bookingId;
    private String customerId;
    private String packageId;
    private String guideId;
    private String hotelOptionId;
    private List<VehicleBookingDTO> vehicleBookings;
    private int noOfChildren;
    private int noOfAdults;
    private LocalDateTime date;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private BigDecimal downPayment;
}

package lk.ijse.tps.tps.hotelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class HotelOption {
    @Id
    private String hotelOptionId;

    @ManyToOne
    @JoinColumn(name = "hotelId", nullable = false)
    private Hotel hotel;

    private String type;
    private String capacity;
    private BigDecimal price;
}

package lk.ijse.tps.tps.hotelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hotel {
    @Id
    private String hotelId;
    private String name;
    private String category;
    private String address;
    private String location;
    private String email;
    private String phone;
    private boolean isPetAllowed;
    private String cancelCriteria;

    @OneToMany(mappedBy = "hotel")
    private List<HotelOption> hotelOptions;
}

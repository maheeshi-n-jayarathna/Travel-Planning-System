package lk.ijse.tps.packageservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Package {
    @Id
    private String packageId;
    private String category;
    private String area;
    private BigDecimal price;
    private int averageNoOfDays;
}

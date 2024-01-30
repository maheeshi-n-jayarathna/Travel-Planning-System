package lk.ijse.tps.packageservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageDTO {
    @Null(message = "Package id auto generated")
    private String packageId;

    @NotNull(message = "InValid category")
    private String category;

    @NotNull(message = "InValid area")
    private String area;

    @NotNull(message = "InValid price")
    @DecimalMin(value = "0.00", message = "InValid price")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "InValid price")
    private BigDecimal price;

    @NotNull(message = "InValid average no of date")
    @Min(value = 0, message = "InValid average no of date")
    private int averageNoOfDays;
}

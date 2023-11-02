package com.example.guideservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GuideDTO implements Serializable {

    private String guideId;
    private String name;
    private String address;
    private String contactNumber;
    private String nic;

    private byte[] profile;
    private byte[] idCardFront;
    private byte[] idCardBack;

    private BigDecimal pricePerDay;

}

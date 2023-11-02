package com.example.guideservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Guide {
    @Id
    private String guideId;
    private String name;
    private String address;
    private String contactNumber;
    private String nic;
    private String profile;
    private String idCardFront;
    private String idCardBack;

    private BigDecimal pricePerDay;
}

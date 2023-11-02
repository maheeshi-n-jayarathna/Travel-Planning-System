package lk.ijse.userservicemysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerId;
    private String name;
    private String email;
    private String nic;
    private String address;

    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String profile;

    private String userName;
    private String password;
}

package lk.ijse.adminservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    private String userId;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String nic;

    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String nicFrontImage;

    @Lob
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String nicBackImage;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String email;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String phone;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String userName;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String userRole;

    @Column(nullable = false,columnDefinition = "LONGTEXT")
    private String password;
}

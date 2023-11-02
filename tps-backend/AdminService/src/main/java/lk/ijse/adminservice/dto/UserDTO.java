package lk.ijse.adminservice.dto;

import lk.ijse.adminservice.dto.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {
    private String userId;
    private String name;
    private String nic;
    private byte[] nicFrontImage;
    private byte[] nicBackImage;
    private String email;
    private String phone;
    private String address;
    private String userName;
    private UserRole userRole;
    private String password;
}

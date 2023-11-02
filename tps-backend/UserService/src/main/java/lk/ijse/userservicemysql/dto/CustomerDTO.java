package lk.ijse.userservicemysql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private String customerId;
    private String name;
    private String email;
    private String nic;
    private String address;
    private byte[] profile;
    private String userName;
    private String password;
}

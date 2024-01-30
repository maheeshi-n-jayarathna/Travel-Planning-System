package lk.ijse.authservice.service;

import lk.ijse.authservice.dto.AuthResponseDTO;
import lk.ijse.authservice.dto.UserDTO;

import java.util.List;

public interface AuthService {
    UserDTO saveUser(UserDTO userDTO);

    String generateToken(String userName);

    AuthResponseDTO generateValidUser(String userName);

    void validateToken(String token);

    List<UserDTO> getAllUser();

    UserDTO getSelectedUser(String userId);

    UserDTO getSelectedUserByUserName(String userName);

    void updateUser(UserDTO userDTO);

    void deleteUser(String userId);
}

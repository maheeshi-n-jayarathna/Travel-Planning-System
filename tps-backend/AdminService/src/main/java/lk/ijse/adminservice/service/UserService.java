package lk.ijse.adminservice.service;

import lk.ijse.adminservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO getSelectedUser(String userId);
    UserDTO getSelectedUserByUserName(String userName);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    List<UserDTO> getAllUser();
    boolean authenticateUser(String userName, String password);

}

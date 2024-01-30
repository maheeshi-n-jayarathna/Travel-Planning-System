package lk.ijse.authservice.service.impl;

import lk.ijse.authservice.dto.AuthResponseDTO;
import lk.ijse.authservice.dto.UserBookingDTO;
import lk.ijse.authservice.dto.UserDTO;
import lk.ijse.authservice.entity.User;
import lk.ijse.authservice.exception.DuplicateException;
import lk.ijse.authservice.exception.InUseException;
import lk.ijse.authservice.exception.InvalidException;
import lk.ijse.authservice.exception.NotFoundException;
import lk.ijse.authservice.persistance.UserDao;
import lk.ijse.authservice.service.AuthService;
import lk.ijse.authservice.service.JwtService;
import lk.ijse.authservice.util.DataTypeConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final DataTypeConvertor convertor;

    @Value("${booking-service-endpoint}")
    private String bookingDataEndPoint;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDao.findByNic(userDTO.getNic()).isPresent())
            throw new DuplicateException("Nic duplicated");
        if (userDao.findByUsername(userDTO.getUsername()).isPresent())
            throw new DuplicateException("userName duplicated");

        String userId;
        do {
            userId = String.format("U%S", UUID.randomUUID());
        } while (userDao.findById(userId).isPresent());
        userDTO.setUserId(userId);

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return convertor.getUserDTO(userDao.save(convertor.getUser(userDTO)));
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public AuthResponseDTO generateValidUser(String userName) {
        User user = userDao.findByUsername(userName).orElseThrow(() -> new NotFoundException("user not found"));
        String token = generateToken(userName);
        return new AuthResponseDTO(user.getUserId(),user.getUsername(),user.getUserRole(),token);
    }

    @Override
    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userDao.findAll().stream().map(convertor::getUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return convertor.getUserDTO(userDao.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public UserDTO getSelectedUserByUserName(String userName) {
        return convertor.getUserDTO(userDao.findByUsername(userName).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = userDao.findById(userDTO.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        if (Objects.equals(user.getUserRole(), "ADMIN")){
            throw new InvalidException("ADMIN can't update");
        }
        Optional<User> optionalUserForNic = userDao.findByNic(userDTO.getNic());
        if (optionalUserForNic.isPresent() && !optionalUserForNic.get().getUserId().equals(userDTO.getUserId()))
            throw new DuplicateException("Duplicate user nic");
        Optional<User> optionalUserForUserName = userDao.findByUsername(userDTO.getUsername());
        if (optionalUserForUserName.isPresent() && !optionalUserForUserName.get().getUserId().equals(userDTO.getUserId()))
            throw new DuplicateException("Duplicate userName");
        userDao.save(convertor.getUser(userDTO));
    }

    @Override
    public void deleteUser(String userId) {
        User user = userDao.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        String userRole = user.getUserRole();
        if (Objects.equals(userRole, "ADMIN")){
            throw new InvalidException("ADMIN can't delete");
        }
        if (userRole != null && userRole.equals("CUSTOMER")) {
            RestTemplate restTemplate = new RestTemplate();
            List<UserBookingDTO> dtos = restTemplate.getForObject(bookingDataEndPoint + "/" + userId, List.class);
            assert dtos != null;
            if (!dtos.isEmpty())
                throw new InUseException("User have booking");
        }
        userDao.deleteById(userId);
    }

}

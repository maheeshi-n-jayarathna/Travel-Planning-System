package lk.ijse.authservice.persistance;

import lk.ijse.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,String> {
    Optional<User> findByUserName(String username);
}

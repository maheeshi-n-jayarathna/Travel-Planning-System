package lk.ijse.adminservice.persistance;

import lk.ijse.adminservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User,String> {
    Optional<User> findByNic(String nic);
    Optional<User> findByUserName(String userName);

    List<User> findAll();
}

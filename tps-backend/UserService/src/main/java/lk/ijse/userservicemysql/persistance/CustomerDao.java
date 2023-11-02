package lk.ijse.userservicemysql.persistance;

import lk.ijse.userservicemysql.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDao extends CrudRepository<Customer,String> {
    List<Customer> findAll();

    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findByNic(String nic);
}

package lk.ijse.tps.packageservice.persistance;

import lk.ijse.tps.packageservice.entity.Package;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PackageDao extends CrudRepository<Package, String> {
    Optional<Package> findByCategoryAndArea(String category,String area);
    List<Package> findAll();
}

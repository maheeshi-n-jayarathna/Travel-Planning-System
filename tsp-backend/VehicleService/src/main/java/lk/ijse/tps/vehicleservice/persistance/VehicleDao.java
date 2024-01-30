package lk.ijse.tps.vehicleservice.persistance;

import lk.ijse.tps.vehicleservice.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface VehicleDao extends CrudRepository<Vehicle, String> {
    Optional<Vehicle> findVehicleByVehicleLicenseNumber(String vehicleLicenseNumber);
    List<Vehicle> findAll();
}

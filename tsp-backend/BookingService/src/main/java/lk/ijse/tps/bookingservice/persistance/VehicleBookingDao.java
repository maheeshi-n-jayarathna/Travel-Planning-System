package lk.ijse.tps.bookingservice.persistance;

import lk.ijse.tps.bookingservice.entity.VehicleBooking;
import lk.ijse.tps.bookingservice.entity.VehicleBookingPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface VehicleBookingDao extends CrudRepository<VehicleBooking, VehicleBookingPk> {
    List<VehicleBooking> findAll();
    List<VehicleBooking> findAllByVehicleId(String vehicleId);
}

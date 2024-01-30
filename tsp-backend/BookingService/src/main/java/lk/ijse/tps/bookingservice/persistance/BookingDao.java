package lk.ijse.tps.bookingservice.persistance;

import lk.ijse.tps.bookingservice.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;



@Repository
public interface BookingDao extends CrudRepository<Booking,String> {
    List<Booking> findAll();
    List<Booking> findAllByCustomerId(String customerId);
    List<Booking> findAllByPackageId(String packageId);
    List<Booking> findAllByGuideId(String guideId);
    List<Booking> findAllByHotelOptionId(String hotelOptionId);
    List<Booking> findAllByDate(LocalDate date);
    List<Booking> findAllByStartDate(LocalDate date);
}

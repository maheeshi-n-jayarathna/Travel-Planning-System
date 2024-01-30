package lk.ijse.tps.tps.hotelservice.persistance;

import lk.ijse.tps.tps.hotelservice.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HotelDao extends CrudRepository<Hotel, String> {
    List<Hotel> findHotelByCategory(String category);

    Optional<Hotel> findHotelByNameAndAddress(String name, String address);

    List<Hotel> findAll();
}


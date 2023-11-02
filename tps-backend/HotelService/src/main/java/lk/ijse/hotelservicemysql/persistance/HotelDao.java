package lk.ijse.hotelservicemysql.persistance;

import lk.ijse.hotelservicemysql.entity.Hotel;
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


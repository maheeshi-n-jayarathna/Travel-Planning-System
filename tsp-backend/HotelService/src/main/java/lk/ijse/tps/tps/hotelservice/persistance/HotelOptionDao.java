package lk.ijse.tps.tps.hotelservice.persistance;

import lk.ijse.tps.tps.hotelservice.entity.HotelOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface HotelOptionDao extends CrudRepository<HotelOption,String> {
    List<HotelOption> findAll();
}

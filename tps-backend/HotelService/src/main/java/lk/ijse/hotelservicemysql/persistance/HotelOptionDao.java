package lk.ijse.hotelservicemysql.persistance;

import lk.ijse.hotelservicemysql.entity.HotelOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelOptionDao extends CrudRepository<HotelOption,String> {
    List<HotelOption> findAll();
}

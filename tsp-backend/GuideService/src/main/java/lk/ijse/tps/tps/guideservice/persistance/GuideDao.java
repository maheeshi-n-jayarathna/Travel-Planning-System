package lk.ijse.tps.tps.guideservice.persistance;

import lk.ijse.tps.tps.guideservice.entity.Guide;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GuideDao extends CrudRepository<Guide,String> {
    Optional<Guide> findGuideByNic(String nic);
    List<Guide> findAll();
}

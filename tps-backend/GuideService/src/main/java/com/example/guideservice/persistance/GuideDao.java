package com.example.guideservice.persistance;

import com.example.guideservice.entity.Guide;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GuideDao extends CrudRepository<Guide, String> {
    Optional<Guide> findGuideByNic(String nic);

    List<Guide> findAll();
}

package lk.ijse.tps.tps.guideservice.service;


import lk.ijse.tps.tps.guideservice.dto.GuideDTO;

import java.util.List;



public interface GuideService {
    GuideDTO saveGuide(GuideDTO guideDTO);
    GuideDTO getSelectedGuide(String guideId);
    void updateGuide(GuideDTO guideDTO);
    void deleteGuide(String guideId);
    List<GuideDTO> getAllGuide();
}

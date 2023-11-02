package com.example.guideservice.service.impl;

import com.example.guideservice.dto.GuideDTO;
import com.example.guideservice.entity.Guide;
import com.example.guideservice.exception.DuplicateException;
import com.example.guideservice.exception.InvalidException;
import com.example.guideservice.exception.NotFoundException;
import com.example.guideservice.persistance.GuideDao;
import com.example.guideservice.service.GuideService;
import com.example.guideservice.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
public class GuideServiceImpl implements GuideService {
    @Autowired
    private GuideDao guideDao;
    @Autowired
    private DataTypeConvertor convertor;
    @Override
    public GuideDTO saveGuide(GuideDTO guideDTO) {
        if(guideDao.findGuideByNic(guideDTO.getNic()).isPresent())
            throw new DuplicateException("Guide nic duplicated");
        String guideId;
        do {
            guideId = String.valueOf(UUID.randomUUID());
        } while (guideDao.findById(guideId).isPresent());
        guideDTO.setGuideId(guideId);
        return convertor.getGuideDTO(guideDao.save(convertor.getGuide(guideDTO)));
    }

    @Override
    public GuideDTO getSelectedGuide(String guideId) {
        return convertor.getGuideDTO(guideDao.findById(guideId).orElseThrow(() -> new NotFoundException("Guide not found")));
    }

    @Override
    public void updateGuide(GuideDTO guideDTO) {
        guideDao.findById(guideDTO.getGuideId()).orElseThrow(() -> new NotFoundException("Guide not found"));
        Optional<Guide> optionalGuide = guideDao.findGuideByNic(guideDTO.getNic());
        if (optionalGuide.isPresent() && !optionalGuide.get().getGuideId().equals(guideDTO.getGuideId()))
            throw new DuplicateException("Guide nic duplicated");
        guideDao.save(convertor.getGuide(guideDTO));
    }

    @Override
    public void deleteGuide(String guideId) {
        guideDao.findById(guideId).orElseThrow(() -> new NotFoundException("Guide not found"));
        // in use
        guideDao.deleteById(guideId);
    }

    @Override
    public List<GuideDTO> getAllGuide() {
        return guideDao.findAll().stream().map(guide -> convertor.getGuideDTO(guide)).collect(Collectors.toList());
    }
}

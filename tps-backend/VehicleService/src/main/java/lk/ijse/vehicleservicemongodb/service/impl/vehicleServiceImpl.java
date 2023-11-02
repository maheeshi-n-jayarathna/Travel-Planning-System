package lk.ijse.vehicleservicemongodb.service.impl;

import lk.ijse.vehicleservicemongodb.dto.VehicleDTO;
import lk.ijse.vehicleservicemongodb.entity.Vehicle;
import lk.ijse.vehicleservicemongodb.exception.DuplicateException;
import lk.ijse.vehicleservicemongodb.exception.NotFoundException;
import lk.ijse.vehicleservicemongodb.persistance.VehicleDao;
import lk.ijse.vehicleservicemongodb.service.VehicleService;
import lk.ijse.vehicleservicemongodb.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class vehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private DataTypeConvertor convertor;

    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) throws IOException {
        if (vehicleDao.findVehicleByVehicleLicenseNumber(vehicleDTO.getVehicleLicenseNumber()).isPresent())
            throw new DuplicateException("Vehicle license number duplicated");
        String vehicleId;
        do {
            vehicleId = String.valueOf(UUID.randomUUID());
        } while (vehicleDao.findById(vehicleId).isPresent());
        vehicleDTO.setVehicleId(vehicleId);
        return convertor.getVehicleDTO(vehicleDao.save(convertor.getVehicle(vehicleDTO)));
    }

    @Override
    public VehicleDTO getSelectedVehicle(String vehicleId) throws IOException {
        return convertor.getVehicleDTO(vehicleDao.findById(vehicleId).orElseThrow(() -> new NotFoundException("Vehicle not found")));
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) throws IOException {
        vehicleDao.findById(vehicleDTO.getVehicleId()).orElseThrow(() -> new NotFoundException("Vehicle not found"));
        Optional<Vehicle> optionalVehicle = vehicleDao.findVehicleByVehicleLicenseNumber(vehicleDTO.getVehicleLicenseNumber());
        if (optionalVehicle.isPresent() && !optionalVehicle.get().getVehicleId().equals(vehicleDTO.getVehicleId()))
            throw new DuplicateException("Duplicate license number");
        vehicleDao.save(convertor.getVehicle(vehicleDTO));
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        vehicleDao.findById(vehicleId).orElseThrow(() -> new NotFoundException("Vehicle not found"));
        // in use check
        vehicleDao.deleteById(vehicleId);
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return vehicleDao.findAll().stream().map(vehicle -> {
            try {
                return convertor.getVehicleDTO(vehicle);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}

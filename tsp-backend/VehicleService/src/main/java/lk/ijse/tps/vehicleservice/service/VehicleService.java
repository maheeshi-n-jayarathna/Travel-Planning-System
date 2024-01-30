package lk.ijse.tps.vehicleservice.service;

import lk.ijse.tps.vehicleservice.dto.VehicleDTO;

import java.util.List;



public interface VehicleService {
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);
    VehicleDTO getSelectedVehicle(String vehicleId);
    void updateVehicle(VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleId);
    List<VehicleDTO> getAllVehicle();
}

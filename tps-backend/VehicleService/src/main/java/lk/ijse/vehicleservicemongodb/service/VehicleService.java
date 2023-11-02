package lk.ijse.vehicleservicemongodb.service;

import lk.ijse.vehicleservicemongodb.dto.VehicleDTO;

import java.io.IOException;
import java.util.List;

public interface VehicleService {
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO) throws IOException;
    VehicleDTO getSelectedVehicle(String vehicleId) throws IOException;
    void updateVehicle(VehicleDTO vehicleDTO) throws IOException;
    void deleteVehicle(String vehicleId);
    List<VehicleDTO> getAllVehicle();
}

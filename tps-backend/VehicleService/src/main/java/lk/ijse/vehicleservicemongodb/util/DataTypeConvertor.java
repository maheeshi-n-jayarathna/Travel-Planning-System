package lk.ijse.vehicleservicemongodb.util;

import lk.ijse.vehicleservicemongodb.dto.VehicleDTO;
import lk.ijse.vehicleservicemongodb.entity.Vehicle;
import lk.ijse.vehicleservicemongodb.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class DataTypeConvertor {
    private final ModelMapper modelMapper;

    public VehicleDTO getVehicleDTO(Vehicle vehicle) throws IOException {
        VehicleDTO vehicleDTO = modelMapper.map(vehicle, VehicleDTO.class);
        vehicleDTO.setVehicleFrontImage(decodeImage(vehicle.getVehicleFrontImage()));
        vehicleDTO.setVehicleRearImage(decodeImage(vehicle.getVehicleRearImage()));
        vehicleDTO.setVehicleSideImage(decodeImage(vehicle.getVehicleSideImage()));
        vehicleDTO.setVehicleFrontInteriorImage(decodeImage(vehicle.getVehicleFrontInteriorImage()));
        vehicleDTO.setVehicleRearInteriorImage(decodeImage(vehicle.getVehicleRearInteriorImage()));
        vehicleDTO.setDriverLicenseFrontImage(decodeImage(vehicle.getDriverLicenseFrontImage()));
        vehicleDTO.setDriverLicenseBackImage(decodeImage(vehicle.getDriverLicenseBackImage()));
        return vehicleDTO;
    }

    public Vehicle getVehicle(VehicleDTO vehicleDTO) throws IOException {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicle.setVehicleFrontImage(encodeImage(vehicleDTO.getVehicleFrontImage()));
        vehicle.setVehicleRearImage(encodeImage(vehicleDTO.getVehicleRearImage()));
        vehicle.setVehicleSideImage(encodeImage(vehicleDTO.getVehicleSideImage()));
        vehicle.setVehicleFrontInteriorImage(encodeImage(vehicleDTO.getVehicleFrontInteriorImage()));
        vehicle.setVehicleRearInteriorImage(encodeImage(vehicleDTO.getVehicleRearInteriorImage()));
        vehicle.setDriverLicenseFrontImage(encodeImage(vehicleDTO.getDriverLicenseFrontImage()));
        vehicle.setDriverLicenseBackImage(encodeImage(vehicleDTO.getDriverLicenseBackImage()));
        return vehicle;
    }

    private String encodeImage(byte[] imageByte) throws IOException {
        return Base64.getEncoder().encodeToString(imageByte);
    }

    private byte[] decodeImage(String imageString) throws IOException {
        return Base64.getDecoder().decode(imageString);
    }
}

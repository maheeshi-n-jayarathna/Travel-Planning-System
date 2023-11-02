package lk.ijse.packegeservicemongodb.service;

import lk.ijse.packegeservicemongodb.dto.PackageDTO;

import java.util.List;

public interface PackageService {
    PackageDTO savePackage(PackageDTO packageDTO);
    PackageDTO getSelectedPackage(String packageId);
    void updatePackage(PackageDTO packageDTO);
    void deletePackage(String packageId);
    List<PackageDTO> getAllPackage();
}

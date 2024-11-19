package com.beysa.services.UserDomain.Clinic;

import com.beysa.services.UserDomain.Clinic.DTO.ClinicDto;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ClinicUtils {
    public ClinicDto convertClinicDto(Clinic clinic){
        ClinicDto response = new ClinicDto();
        response.setIdClinic(clinic.getIdClinic());
        response.setName(clinic.getName());
        response.setLegalRepresentative(clinic.getLegalRepresentative());
        response.setTaxIdentificationNumber(clinic.getTaxIdentificationNumber());
        response.setBusinessName(clinic.getBusinessName());
        response.setCreateAd(clinic.getCreateAd());
        response.setUpgradeAd(clinic.getUpgradeAd());
        response.setLogo(clinic.getLogo());
        response.setIdGeographicalLocation(clinic.getGeographicalLocation().getIdGeographicalLocation());
        response.setEmail(clinic.getEmail());
        response.setMedicalRecordNumber(clinic.getMedicalRecordNumber());
        response.setIsBranch(clinic.getIsBranch());
        response.setBranchNumber(clinic.getBranchNumber());
        response.setIsMain(clinic.getIsMain());
        response.setPhone(clinic.getPhone());
        response.setContactMobile(clinic.getContactMobile());
        response.setAddress(clinic.getAddress());
        response.setStatus(clinic.getStatus());
        return response;
    }

    public List<ClinicDto> convertListClinicDto(List<Clinic> listClinic){
        return listClinic.stream()
                .map(this::convertClinicDto)
                .collect(Collectors.toList());

    }
}

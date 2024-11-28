package com.beysa.services.UserDomain.Medic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;

@Component
public class MedicUtils {
    public MedicDto convertMedicDto(Medic medic) {
        if (medic == null) return null;
        MedicDto dto = new MedicDto();
        dto.setIdMedic(medic.getIdMedic());
        dto.setIdSpeciality(medic.getSpeciality().getIdSpeciality());
        dto.setIdStaff(medic.getStaff().getIdStaff());
        dto.setProfessionalLicenseNumber(medic.getProfessionalLicenseNumber());
        dto.setCreateAd(medic.getCreateAd());
        dto.setUpdateAd(medic.getUpdateAd());
        dto.setStatus(medic.getStatus());
        dto.setSignature(medic.getSignature());
        dto.setSloganMedic(medic.getSloganMedic());
        return dto;
    }

    public List<MedicDto> convertListMedicDto(List<Medic> medicList) {
        return medicList.stream()
                        .map(this::convertMedicDto)
                        .collect(Collectors.toList());
    }
}

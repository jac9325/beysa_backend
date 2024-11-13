package com.beysa.services.UserDomain.Medic;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Medic.DTO.MedicDtos;

@Component
public class MedicUtils {
     public MedicDtos convMedicDtos(Medic medic) {
        if (medic == null) {
            return null;
        }
        
        MedicDtos dto = new MedicDtos();
        dto.setIdMedic(medic.getIdMedic());
        dto.setIdSpeciality(medic.getSpeciality().getIdSpeciality());
        dto.setIdStaff(medic.getStaff().getIdStaff());
        dto.setProfessionalLicenseNumber(medic.getProfessionalLicenseNumber());
        dto.setCreateAd(medic.getCreateAd());
        dto.setUpdateAd(medic.getUpdateAd());
        dto.setStatus(medic.getStatus());

        return dto;
    }

    public List<MedicDtos> converListMedicDtos(List<Medic> medicList) {
        return medicList.stream()
                        .map(this::convMedicDtos)
                        .collect(Collectors.toList());
    }
}

package com.beysa.services.UserDomain.PatientClinic;

import com.beysa.services.UserDomain.PatientClinic.DTO.PatientClinicDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientClinicUtils {
    public PatientClinicDto convertPatientClinicDto(PatientClinic entity) {
        if (entity == null) return null;
        PatientClinicDto dto = new PatientClinicDto();
        dto.setIdPatientClinic(entity.getIdPatientClinic());
        dto.setIdPatient(entity.getPatient().getIdPatient());
        dto.setIdPatient(entity.getClinic().getIdClinic());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setEmail(entity.getEmail());
        dto.setIdGeographicalLocation(entity.getGeographicalLocation().getIdGeographicalLocation());
        dto.setIdIdentityDocument(entity.getIdentityDocument().getIdIdentityDocument());
        dto.setAddress(entity.getAddress());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setIdentityDocumentNumber(entity.getIdentityDocumentNumber());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<PatientClinicDto> convertListPatientClinicDto(List<PatientClinic> entities) {
        return entities.stream()
                .map(this::convertPatientClinicDto)
                .collect(Collectors.toList());
    }
}

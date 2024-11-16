package com.beysa.services.UserDomain.Patient;

import com.beysa.services.UserDomain.Patient.DTO.PatientDto;

import java.util.List;
import java.util.stream.Collectors;

public class PatientUtils {
    public PatientDto convertPatientDto(Patient patient){
        PatientDto response = new PatientDto();
        response.setIdPatient(patient.getIdPatient());
        response.setFirstName(patient.getFirstName());
        response.setLastName(patient.getLastName());
        response.setMobileNumber(patient.getMobileNumber());
        response.setEmail(patient.getEmail());
        response.setIdGeographicalLocation(patient.getGeographicalLocation().getIdGeographicalLocation());
        response.setIdIdentityDocument(patient.getIdentityDocument().getIdIdentityDocument());
        response.setAddress(patient.getAddress());
        response.setAge(patient.getAge());
        response.setGender(patient.getGender());
        response.setIdentityDocumentNumber(patient.getIdentityDocumentNumber());
        response.setStatus(patient.getStatus());
        return response;
    }

    public List<PatientDto> convertListPatientDto(List<Patient> listPatient){
        return listPatient.stream()
                .map(this::convertPatientDto)
                .collect(Collectors.toList());
    }

}

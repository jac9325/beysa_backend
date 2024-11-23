package com.beysa.services.UserDomain.PatientClinic;

import com.beysa.services.UserDomain.PatientClinic.DTO.PatientClinicDto;

import java.util.List;

public interface PatientClinicService {
    PatientClinicDto getPatientClinicById(Long idPatientClinic);
    List<PatientClinicDto> getPatientClinicByIdClinic(Long idClinic);
}

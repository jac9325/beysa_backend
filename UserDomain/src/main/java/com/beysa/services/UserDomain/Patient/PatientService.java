package com.beysa.services.UserDomain.Patient;

import com.beysa.services.UserDomain.Patient.DTO.PatientDto;
import java.util.List;

public interface PatientService {
    PatientDto getPatientById(Long idPatient);
    List<PatientDto> getAllPatient();
}

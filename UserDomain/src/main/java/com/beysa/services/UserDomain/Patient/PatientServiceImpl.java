package com.beysa.services.UserDomain.Patient;

import com.beysa.services.UserDomain.Patient.DTO.PatientDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PatientServiceImpl implements PatientService{
    
    private final PatientRepository patientRepository;
    private final PatientUtils patientUtils;

    public PatientServiceImpl(PatientRepository patientRepository, PatientUtils patientUtils){
        this.patientRepository = patientRepository;
        this.patientUtils = patientUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public PatientDto getPatientById(Long idPatient){
        return patientRepository.findById(idPatient)
                .map(patientUtils::convertPatientDto)
                .orElseThrow(() -> new RuntimeException("Patient not found for id: " + idPatient));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PatientDto> getAllPatient(){
        List<Patient> listPatient = patientRepository.findAll();
        if(listPatient.isEmpty()){
            throw new RuntimeException("No Patient records found in the database");
        }
        return patientUtils.convertListPatientDto(listPatient);
    }
}
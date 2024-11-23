package com.beysa.services.UserDomain.Patient;

import com.beysa.services.UserDomain.Patient.DTO.PatientDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
    
    private final PatientRepository patientRepository;
    private final PatientUtils patientUtils;

    @Transactional(readOnly = true)
    @Override
    public PatientDto getPatientById(Long idPatient){
        return patientRepository.findById(idPatient)
                .map(patientUtils::convertPatientDto)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado por el id: " + idPatient));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PatientDto> getAllPatient(){
        List<Patient> listPatient = patientRepository.findAll();
        if(listPatient.isEmpty()){
            throw new RuntimeException("No se encontraron registros de Paciente en la base de datos.");
        }
        return patientUtils.convertListPatientDto(listPatient);
    }
}

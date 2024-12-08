package com.beysa.services.UserDomain.PatientClinic;

import com.beysa.services.UserDomain.PatientClinic.DTO.PatientClinicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientClinicServiceImpl implements PatientClinicService{
    private final PatientClinicRepository patientClinicRepository;
    private final PatientClinicUtils patientClinicUtils;

    @Transactional(readOnly = true)
    @Override
    public PatientClinicDto getPatientClinicById(Long idPatientClinic){
        return patientClinicRepository.findById(idPatientClinic)
                .map(patientClinicUtils::convertPatientClinicDto)
                .orElseThrow(() -> new RuntimeException("Paciente-Clínica no encontrado por el id: " + idPatientClinic));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PatientClinicDto> getPatientClinicByIdClinic(Long idClinic){
        List<PatientClinic> patientClinic = patientClinicRepository.findByIdClinic(idClinic);
        if (patientClinic.isEmpty()) {
            throw new RuntimeException("No se encontraron registros de Paciente-Clínica en la base de datos.");
        }
        return patientClinicUtils.convertListPatientClinicDto(patientClinic);
    }
}

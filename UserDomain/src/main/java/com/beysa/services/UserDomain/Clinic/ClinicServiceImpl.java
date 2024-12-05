package com.beysa.services.UserDomain.Clinic;

import com.beysa.services.UserDomain.Clinic.DTO.ClinicAndConfigurationDto;
import com.beysa.services.UserDomain.Clinic.DTO.ClinicDto;
import com.beysa.services.UserDomain.ClinicConfiguration.ClinicConfigurationService;
import com.beysa.services.UserDomain.ClinicConfiguration.DTO.ClinicConfigurationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService{

    private final ClinicRepository clinicRepository;
    private final ClinicUtils clinicUtils;
    private final ClinicConfigurationService clinicConfigurationService;

    @Transactional(readOnly = true)
    @Override
    public ClinicDto getClinicById(Long idClinic){
        return clinicRepository.findById(idClinic)
                .map(clinicUtils::convertClinicDto)
                .orElseThrow(() -> new RuntimeException("Clínica no encontrado por el id: " + idClinic));
    }

    @Transactional(readOnly = true)
    @Override
    public Clinic getClinicByIdEntity(Long idClinic){
        return clinicRepository.findById(idClinic)
                .orElseThrow(() -> new RuntimeException("Clínica no encontrado por el id: " + idClinic));
    }

    @Transactional
    @Override
    public ClinicAndConfigurationDto getClinicAndConfigurationByIdUser(Long idUser){
        /*Los servicios manejan RuntimeException para la validación*/
        Clinic clinic = clinicRepository.findClinicByUser(idUser).orElse(null);
        if (clinic == null){
            throw new RuntimeException("Ha ocurrido un error al obtener la Clinica");
        }
        ClinicDto currentClinic = clinicUtils.convertClinicDto(clinic);
        ClinicConfigurationDto clinicConfiguration = clinicConfigurationService.getClinicConfigurationByIdClinic(currentClinic.getIdClinic());

        ClinicAndConfigurationDto clinicAndConfiguration = new ClinicAndConfigurationDto();
        clinicAndConfiguration.setClinic(currentClinic);
        clinicAndConfiguration.setClinicConfiguration(clinicConfiguration);
        return clinicAndConfiguration;
    }

    @Transactional
    @Override
    public ClinicAndConfigurationDto getClinicAndConfigurationById(Long idClinic){
        /*Los servicios manejan RuntimeException para la validación*/
        ClinicDto clinic = getClinicById(idClinic);
        ClinicConfigurationDto clinicConfiguration = clinicConfigurationService.getClinicConfigurationByIdClinic(idClinic);

        ClinicAndConfigurationDto clinicAndConfiguration = new ClinicAndConfigurationDto();
        clinicAndConfiguration.setClinic(clinic);
        clinicAndConfiguration.setClinicConfiguration(clinicConfiguration);
        return clinicAndConfiguration;
    }
}

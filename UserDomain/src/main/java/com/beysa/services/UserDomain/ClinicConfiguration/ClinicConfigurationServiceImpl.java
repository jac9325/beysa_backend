package com.beysa.services.UserDomain.ClinicConfiguration;

import com.beysa.services.UserDomain.ClinicConfiguration.DTO.ClinicConfigurationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClinicConfigurationServiceImpl implements ClinicConfigurationService{

    private final ClinicConfigurationRepository clinicConfigurationRepository;
    private final ClinicConfigurationUtils clinicConfigurationUtils;

    public ClinicConfigurationServiceImpl(ClinicConfigurationRepository clinicConfigurationRepository, ClinicConfigurationUtils clinicConfigurationUtils){
        this.clinicConfigurationRepository = clinicConfigurationRepository;
        this.clinicConfigurationUtils = clinicConfigurationUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public ClinicConfigurationDto getClinicConfigurationById(Long idClinicConfiguration){
        return clinicConfigurationRepository.findById(idClinicConfiguration)
                .map(clinicConfigurationUtils::convertClinicConfigurationDto)
                .orElseThrow(() -> new RuntimeException("Configuración de Clínica no encontrado por el id: " + idClinicConfiguration));
    }

    @Transactional(readOnly = true)
    @Override
    public ClinicConfigurationDto getClinicConfigurationByIdClinic(Long idClinic){
        return clinicConfigurationRepository.findByIdClinic(idClinic)
                .map(clinicConfigurationUtils::convertClinicConfigurationDto)
                .orElseThrow(() -> new RuntimeException("Configuración de Clínica no encontrado por el id clínica: " + idClinic));
    }
}

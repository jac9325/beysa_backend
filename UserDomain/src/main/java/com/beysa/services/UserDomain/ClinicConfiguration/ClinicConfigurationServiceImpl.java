package com.beysa.services.UserDomain.ClinicConfiguration;

import com.beysa.services.UserDomain.ClinicConfiguration.DTO.ClinicConfigurationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClinicConfigurationServiceImpl implements ClinicConfigurationService{

    private final ClinicConfigurationRepository clinicConfigurationRepository;
    private final ClinicConfigurationUtils clinicConfigurationUtils;

    @Transactional(readOnly = true)
    @Override
    public ClinicConfigurationDto getClinicConfigurationById(Long idClinicConfiguration){
        return clinicConfigurationRepository.findById(idClinicConfiguration)
                .map(clinicConfigurationUtils::convertClinicConfigurationDto)
                .orElseThrow(() -> new RuntimeException("ClinicConfiguration not found for id: " + idClinicConfiguration));
    }

    @Transactional(readOnly = true)
    @Override
    public ClinicConfigurationDto getClinicConfigurationByIdClinic(Long idClinic){
        return clinicConfigurationRepository.findByIdClinic(idClinic)
                .map(clinicConfigurationUtils::convertClinicConfigurationDto)
                .orElseThrow(() -> new RuntimeException("ClinicConfiguration not found for idClinic: " + idClinic));
    }
}

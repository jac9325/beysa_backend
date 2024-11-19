package com.beysa.services.UserDomain.Clinic;

import com.beysa.services.UserDomain.Clinic.DTO.ClinicAndConfigurationDto;
import com.beysa.services.UserDomain.Clinic.DTO.ClinicDto;
import com.beysa.services.UserDomain.ClinicConfiguration.ClinicConfigurationService;
import com.beysa.services.UserDomain.ClinicConfiguration.DTO.ClinicConfigurationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClinicServiceImpl implements ClinicService{

    private final ClinicRepository clinicRepository;
    private final ClinicUtils clinicUtils;
    private final ClinicConfigurationService clinicConfigurationService;

    public ClinicServiceImpl(ClinicRepository clinicRepository, ClinicUtils clinicUtils, ClinicConfigurationService clinicConfigurationService){
        this.clinicRepository = clinicRepository;
        this.clinicUtils = clinicUtils;
        this.clinicConfigurationService = clinicConfigurationService;
    }

    @Transactional(readOnly = true)
    @Override
    public ClinicDto getClinicById(Long idClinic){
        return clinicRepository.findById(idClinic)
                .map(clinicUtils::convertClinicDto)
                .orElseThrow(() -> new RuntimeException("Clinic not found for id: " + idClinic));
    }

    @Transactional(readOnly = true)
    @Override
    public Clinic getClinicByIdEntity(Long idClinic){
        return clinicRepository.findById(idClinic)
                .orElseThrow(() -> new RuntimeException("Clinic not found for id: " + idClinic));
    }

    // @Transactional
    // @Override
    // public ClinicAndConfigurationDto getClinicAndConfigurationById(Long idClinic){
    //     /*Los servicios manejan RuntimeException para la validación*/
    //     ClinicConfigurationDto clinicConfiguration = clinicConfigurationService.getClinicConfigurationByIdClinic(idClinic);
    //     ClinicDto clinic = getClinicById(idClinic);

    //     ClinicAndConfigurationDto clinicAndConfiguration = new ClinicAndConfigurationDto();
    //     clinicAndConfiguration.setClinic(clinic);
    //     clinicAndConfiguration.setClinicConfiguration(clinicConfiguration);
    //     return clinicAndConfiguration;
    // }
}

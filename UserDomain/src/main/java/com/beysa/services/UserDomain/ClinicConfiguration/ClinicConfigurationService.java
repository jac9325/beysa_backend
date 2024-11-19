package com.beysa.services.UserDomain.ClinicConfiguration;

import com.beysa.services.UserDomain.ClinicConfiguration.DTO.ClinicConfigurationDto;

public interface ClinicConfigurationService {
    ClinicConfigurationDto getClinicConfigurationById(Long idClinicConfiguration);
    // ClinicConfigurationDto getClinicConfigurationByIdClinic(Long idClinic);
}

package com.beysa.services.UserDomain.Clinic;

import com.beysa.services.UserDomain.Clinic.DTO.ClinicAndConfigurationDto;
import com.beysa.services.UserDomain.Clinic.DTO.ClinicDto;

public interface ClinicService {
    ClinicDto getClinicById(Long idClinic);
    Clinic getClinicByIdEntity(Long idClinic);
    // ClinicAndConfigurationDto getClinicAndConfigurationById(Long idClinic);
}

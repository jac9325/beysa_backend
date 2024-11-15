package com.beysa.services.UserDomain.Clinic.DTO;

import com.beysa.services.UserDomain.ClinicConfiguration.DTO.ClinicConfigurationDto;
import lombok.Data;

@Data
public class ClinicAndConfigurationDto {
    ClinicDto clinic;
    ClinicConfigurationDto clinicConfiguration;
}

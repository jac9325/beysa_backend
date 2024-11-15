package com.beysa.services.UserDomain.ClinicConfiguration;

import com.beysa.services.UserDomain.ClinicConfiguration.DTO.ClinicConfigurationDto;

import java.util.List;
import java.util.stream.Collectors;

public class ClinicConfigurationUtils {
    public ClinicConfigurationDto convertClinicConfigurationDto(ClinicConfiguration clinicConfiguration){
        ClinicConfigurationDto response = new ClinicConfigurationDto();
        response.setIdClinicConfiguration(clinicConfiguration.getIdClinicConfiguration());
        response.setStarDay(clinicConfiguration.getStarDay());
        response.setDecimalNumber(clinicConfiguration.getDecimalNumber());
        response.setHistoryNumber(clinicConfiguration.getHistoryNumber());
        response.setPaginationNumber(clinicConfiguration.getPaginationNumber());
        response.setPreInvoiceNumber(clinicConfiguration.getPreInvoiceNumber());
        response.setIdClinic(clinicConfiguration.getClinic().getIdClinic());
        response.setStatus(clinicConfiguration.getStatus());
        return response;
    }

    public List<ClinicConfigurationDto> convertListClinicConfigurationDto(List<ClinicConfiguration> listClinicConfiguration){
        return listClinicConfiguration.stream()
                .map(this::convertClinicConfigurationDto)
                .collect(Collectors.toList());

    }
}

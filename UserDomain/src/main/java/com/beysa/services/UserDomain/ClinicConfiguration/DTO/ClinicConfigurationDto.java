package com.beysa.services.UserDomain.ClinicConfiguration.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicConfigurationDto {
    private Long idClinicConfiguration;
    private String starDay;
    private Integer decimalNumber;
    private String historyNumber;
    private Integer paginationNumber;
    private String preInvoiceNumber;
    private Long idClinic;
    private Integer status;
}

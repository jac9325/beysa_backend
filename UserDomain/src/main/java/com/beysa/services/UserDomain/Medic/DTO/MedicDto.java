package com.beysa.services.UserDomain.Medic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicDto {
    private Long idMedic;
    private Long idSpeciality;
    private Long idStaff;
    private String professionalLicenseNumber;
    private LocalDateTime createAd;
    private LocalDateTime updateAd;
    private Integer status;
    private String signature;
    private String sloganMedic;
}

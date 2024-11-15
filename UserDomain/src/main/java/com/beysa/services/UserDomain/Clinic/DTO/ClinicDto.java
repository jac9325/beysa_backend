package com.beysa.services.UserDomain.Clinic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDto {
    private Long idClinic;
    private String name;
    private String legalRepresentative;
    private String taxIdentificationNumber;
    private String businessName;
    private LocalDateTime createAd;
    private LocalDateTime upgradeAd;
    private String logo;
    private Long idGeographicalLocation;
    private String email;
    private Integer medicalRecordNumber;
    private Integer isBranch;
    private Integer branchNumber;
    private Integer isMain;
    private String phone;
    private String contactMobile;
    private String address;
    private Integer status;
}

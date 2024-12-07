package com.beysa.services.UserDomain.PatientClinic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientClinicDto {
    private Long idPatientClinic;
    private Long idPatient;
    private Long idClinic;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Long idGeographicalLocation;
    private Long idIdentityDocument;
    private String address;
    private String age;
    private String gender;
    private String identityDocumentNumber;
    private Integer status;
}

package com.beysa.services.UserDomain.Patient.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long idPatient;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Long IdGeographicalLocation;
    private Long IdIdentityDocument;
    private String address;
    private String age;
    private String gender;
    private String identityDocumentNumber;
    private Integer status;
}

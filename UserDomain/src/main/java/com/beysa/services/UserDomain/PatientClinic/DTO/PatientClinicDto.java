package com.beysa.services.UserDomain.PatientClinic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientClinicDto {
    Long idPatientClinic;
    Long idPatient;
    Long idClinic;
    Integer status;
    String firstName;
    String lastName;
    String mobileNumber;
    String email;
    String address;
    String age;
    String gender;
    String identityDocumentNumber;
    Long idIdentityDocument;
    Long idGeographicalLocation;
}

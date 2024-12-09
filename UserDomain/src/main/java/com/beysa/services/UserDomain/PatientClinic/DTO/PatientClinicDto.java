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
    String first_name;
    String last_name;
    String mobile_number;
    String email;
    String address;
    String age;
    String gender;
    String identity_document_number;
    Long idIdentityDocument;
    Long idGeographicalLocation;
}

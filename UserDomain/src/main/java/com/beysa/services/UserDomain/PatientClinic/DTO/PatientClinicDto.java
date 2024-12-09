package com.beysa.services.UserDomain.PatientClinic.DTO;

import com.beysa.services.UserDomain.GeographicalLocation.GeographicalLocation;
import com.beysa.services.UserDomain.IdentityDocument.IdentityDocument;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

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

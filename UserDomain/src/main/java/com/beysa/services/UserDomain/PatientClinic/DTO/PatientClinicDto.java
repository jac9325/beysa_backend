package com.beysa.services.UserDomain.PatientClinic.DTO;

import lombok.Data;

@Data
public class PatientClinicDto {
    Long idPatientClinic;
    Long idPatient;
    Long idClinic;
    Integer status;
}

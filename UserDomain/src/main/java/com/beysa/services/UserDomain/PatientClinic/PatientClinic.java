package com.beysa.services.UserDomain.PatientClinic;

import com.beysa.services.UserDomain.Clinic.Clinic;
import com.beysa.services.UserDomain.Patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_patient_clinic")
public class PatientClinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_patient_clinic")
    private Long idPatientClinic;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;

    @Column(name = "status")
    private Integer status;
}

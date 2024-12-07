package com.beysa.services.UserDomain.PatientClinic;

import com.beysa.services.UserDomain.Clinic.Clinic;
import com.beysa.services.UserDomain.GeographicalLocation.GeographicalLocation;
import com.beysa.services.UserDomain.IdentityDocument.IdentityDocument;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_geographical_location")
    private GeographicalLocation geographicalLocation;

    @ManyToOne
    @JoinColumn(name = "id_identity_document")
    private IdentityDocument identityDocument;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private String age;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "identity_document_number")
    private String identityDocumentNumber;

    @Column(name = "status")
    private Integer status;
}

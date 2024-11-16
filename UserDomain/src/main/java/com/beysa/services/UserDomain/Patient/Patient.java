package com.beysa.services.UserDomain.Patient;

import com.beysa.services.UserDomain.GeographicalLocation.GeographicalLocation;
import com.beysa.services.UserDomain.IdentityDocument.IdentityDocument;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_patient")
    private Long idPatient;

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

    @OneToOne
    @JoinColumn(name = "id_identify_document")
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

package com.beysa.services.UserDomain.Clinic;

import com.beysa.services.UserDomain.GeographicalLocation.GeographicalLocation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_clinic")
    private Long idClinic;

    @Column(name = "name")
    private String name;

    @Column(name = "legal_representative")
    private String legalRepresentative;

    @Column(name = "tax_identification_number", length = 11)
    private String taxIdentificationNumber;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "create_ad")
    private LocalDateTime createAd;

    @Column(name = "upgrade_ad")
    private LocalDateTime upgradeAd;

    @Column(name = "logo")
    private String logo;

    @OneToOne
    @JoinColumn(name = "id_geographical_Location")
    private GeographicalLocation geographicalLocation;

    @Column(name = "email")
    private String email;

    @Column(name = "medical_record_number")
    private Integer medicalRecordNumber;

    @Column(name = "is_branch")
    private Integer isBranch;

    @Column(name = "branch_number")
    private Integer branchNumber;

    @Column(name = "is_main")
    private Integer isMain;

    @Column(name = "phone")
    private String phone;

    @Column(name = "contact_mobile")
    private String contactMobile;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private Integer status;
}

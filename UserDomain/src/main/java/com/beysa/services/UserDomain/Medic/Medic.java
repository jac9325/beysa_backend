package com.beysa.services.UserDomain.Medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.beysa.services.UserDomain.Speciality.Speciality;
import com.beysa.services.UserDomain.Staff.Staff;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_medic")
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_medic")
    private Long idMedic;

    @ManyToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "id_speciality")
    private Speciality Speciality;

    @Column(name = "professional_license_number")
    private String professionalLicenseNumber;

    @Column(name = "create_ad")
    private LocalDateTime createAd;

    @Column(name = "update_ad")
    private LocalDateTime updateAd;

    @Column(name = "status")
    private Integer status;
}

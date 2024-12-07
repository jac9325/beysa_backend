package com.beysa.services.UserDomain.Treatment;

import com.beysa.services.UserDomain.Clinic.Clinic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_treatment")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_treatment")
    private Long idTreatment;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "create_ad")
    private LocalDateTime createAd;

    @ManyToOne
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;

    @Column(name = "status")
    private Integer status;
}

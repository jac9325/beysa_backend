package com.beysa.services.UserDomain.Speciality;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_speciality")
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_speciality")
    private Long idSpeciality;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;
}

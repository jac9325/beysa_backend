package com.beysa.services.UserDomain.Department;

import com.beysa.services.UserDomain.Country.Country;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_department")
    private Long idDepartment;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_country")
    private Country country;

    @Column(name = "status")
    private Integer status;
}

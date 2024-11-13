package com.beysa.services.UserDomain.GeographicalLocation.Department;

import com.beysa.services.UserDomain.GeographicalLocation.Country.CountryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_department")
    private Long idDepartment;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_country")
    private CountryEntity idCountry;

    @Column(name = "status")
    private Integer status;
}

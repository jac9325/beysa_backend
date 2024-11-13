package com.beysa.services.UserDomain.GeographicalLocation;

import com.beysa.services.UserDomain.Country.Country;
import com.beysa.services.UserDomain.Department.Department;
import com.beysa.services.UserDomain.District.District;
import com.beysa.services.UserDomain.Province.Province;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_geographical_location")
public class GeographicalLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_geographical_location")
    private Long idGeographicalLocation;

    @ManyToOne
    @JoinColumn(name = "id_country")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "id_district")
    private District district;

    @Column(name = "status")
    private Integer status;
}

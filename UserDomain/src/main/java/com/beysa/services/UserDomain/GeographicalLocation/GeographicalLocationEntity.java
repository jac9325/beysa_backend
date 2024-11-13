package com.beysa.services.UserDomain.GeographicalLocation;

import com.beysa.services.UserDomain.GeographicalLocation.Country.CountryEntity;
import com.beysa.services.UserDomain.GeographicalLocation.Department.DepartmentEntity;
import com.beysa.services.UserDomain.GeographicalLocation.District.DistrictEntity;
import com.beysa.services.UserDomain.GeographicalLocation.Province.ProvinceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_geographical_location")
public class GeographicalLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_geographical_location")
    private Long idGeographicalLocation;

    @ManyToOne
    @JoinColumn(name = "id_country")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "id_province")
    private ProvinceEntity province;

    @ManyToOne
    @JoinColumn(name = "id_district")
    private DistrictEntity district;

    @Column(name = "status")
    private Integer status;
}

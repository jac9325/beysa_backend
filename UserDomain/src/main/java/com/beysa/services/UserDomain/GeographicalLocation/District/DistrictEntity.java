package com.beysa.services.UserDomain.GeographicalLocation.District;

import com.beysa.services.UserDomain.GeographicalLocation.Province.ProvinceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_district")
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_district")
    private Long idDistrict;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @Column(name = "id_province")
    private ProvinceEntity province;

    @Column(name = "status")
    private Integer status;
}

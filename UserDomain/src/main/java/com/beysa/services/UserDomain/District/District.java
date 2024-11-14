package com.beysa.services.UserDomain.District;

import com.beysa.services.UserDomain.Province.Province;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_district")
    private Long idDistrict;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;

    @Column(name = "status")
    private Integer status;
}

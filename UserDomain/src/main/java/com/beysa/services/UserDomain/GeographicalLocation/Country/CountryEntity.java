package com.beysa.services.UserDomain.GeographicalLocation.Country;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_country")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_country")
    private Long idCountry;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;
}

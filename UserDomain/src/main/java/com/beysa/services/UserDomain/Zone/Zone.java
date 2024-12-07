package com.beysa.services.UserDomain.Zone;

import com.beysa.services.UserDomain.Treatment.Treatment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zone")
    private Long idZone;

    @ManyToOne
    @JoinColumn(name = "id_treatment")
    private Treatment treatment;

    @Column(name = "mane")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name= "number_sessions")
    private Integer numberSessions;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "status")
    private Integer status;

}

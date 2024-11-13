package com.beysa.services.UserDomain.Province;

import com.beysa.services.UserDomain.Department.Department;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_province")
    private Long idProvince;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department department;

    @Column(name = "status")
    private Integer status;
}

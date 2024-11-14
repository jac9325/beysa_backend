package com.beysa.services.UserDomain.Permissions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_permissions")
public class PermissionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_permissions")
    private Long id_permissions;

    @NotBlank
    @Column(name = "name")
    private String name;
    
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "status")
    private Integer status;

}


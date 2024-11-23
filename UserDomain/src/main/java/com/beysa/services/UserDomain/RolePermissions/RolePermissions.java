package com.beysa.services.UserDomain.RolePermissions;

import com.beysa.services.UserDomain.Permissions.PermissionsEntity;
import com.beysa.services.UserDomain.Rol.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_role_permissions")
public class RolePermissions{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role_permissions")
    private long idRolePermissions;
    
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
    
    @ManyToOne
    @JoinColumn(name = "id_permissions")
    private PermissionsEntity permissions;

    @Column(name = "status")
    private Integer status;
          
}
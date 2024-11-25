package com.beysa.services.UserDomain.RolePermissions.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionsDto {
    private Long idRolePermissions;
    private Long idRol;
    private Long idPermissions;
    private Integer status;
}

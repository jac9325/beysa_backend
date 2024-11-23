package com.beysa.services.UserDomain.RolePermissions;

import com.beysa.services.UserDomain.RolePermissions.DTO.RolePermissionsDto;

import java.util.List;

public interface RolePermissionsService {
    List<RolePermissionsDto> getRolePermissionsByIdRole(Long idRole);
}

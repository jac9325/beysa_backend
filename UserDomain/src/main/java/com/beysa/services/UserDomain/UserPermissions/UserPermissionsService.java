package com.beysa.services.UserDomain.UserPermissions;

import java.util.List;

import com.beysa.services.UserDomain.UserPermissions.DTO.UserPermissionsDto;

public interface UserPermissionsService {
    List<UserPermissions> getAllPermissions();
    UserPermissions save(UserPermissions userPermissions);
    Boolean saveAllPermissions(List<UserPermissions> list);
    UserPermissions getUserPermissionsById(Long idUserPermissions);
    Boolean deteleUserPermisisions(List<UserPermissionsDto> userPermissionsDtos);
    Boolean addUserPermisisions(List<UserPermissionsDto> userPermissionsDtos);
}

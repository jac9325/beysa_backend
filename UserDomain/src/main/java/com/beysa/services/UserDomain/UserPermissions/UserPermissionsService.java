package com.beysa.services.UserDomain.UserPermissions;

import java.util.List;

public interface UserPermissionsService {
    List<UserPermissions> getAllPermissions();
    UserPermissions save(UserPermissions userPermissions);
    Boolean saveAllPermissions(List<UserPermissions> list);
    UserPermissions getUserPermissionsById(Long idUserPermissions);
}

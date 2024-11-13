package com.beysa.services.UserDomain.Permissions;

import java.util.List;

import com.beysa.services.UserDomain.Rol.Rol;

public interface PermissionsService {
    public Boolean createUserAll(PermissionsEntity currentPermissions, List<Permissions> currentPermissions);
}
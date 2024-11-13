package com.beysa.services.UserDomain.UserRoles;

import java.util.List;

public interface UserRolService {
    List<UserRol> getAllRol();
    UserRol save(UserRol usuarioRol);
}
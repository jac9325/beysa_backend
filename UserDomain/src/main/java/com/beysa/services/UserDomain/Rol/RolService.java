package com.beysa.services.UserDomain.Rol;

import java.util.List;

public interface RolService {
    Rol getRolById(long id);
    List<Rol> getAllRol();
    Rol getRolByName(String name);
}
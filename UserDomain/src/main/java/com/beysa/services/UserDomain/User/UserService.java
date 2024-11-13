package com.beysa.services.UserDomain.User;

import java.util.List;

import com.beysa.services.UserDomain.Rol.Rol;

public interface UserService {
    public Boolean createUserAll(UserEntity currentUser, List<Rol> currentRols);
}
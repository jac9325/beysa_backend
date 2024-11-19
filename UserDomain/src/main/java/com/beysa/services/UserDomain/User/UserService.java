package com.beysa.services.UserDomain.User;

import java.util.List;

import com.beysa.services.UserDomain.Clinic.Clinic;
import com.beysa.services.UserDomain.Rol.Rol;

public interface UserService {
    UserEntity createUserAll(UserEntity currentUser, List<Rol> currentRols, Clinic clinic);
    UserEntity createUsuario(UserEntity request);
}
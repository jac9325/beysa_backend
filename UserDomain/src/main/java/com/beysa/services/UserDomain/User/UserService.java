package com.beysa.services.UserDomain.User;

import java.util.List;

import com.beysa.services.UserDomain.Clinic.Clinic;
import com.beysa.services.UserDomain.Rol.Rol;
import com.beysa.services.UserDomain.User.DTO.UserSend;

public interface UserService {
    UserEntity createUserAll(UserEntity currentUser, List<Rol> currentRols, Clinic clinic);
    UserEntity createUsuario(UserEntity request);
    Boolean changePasswordStaff(UserEntity newUser);
    UserEntity getUserById(Long idUser);
    UserSend findByUsernameUserSend(String username);
}
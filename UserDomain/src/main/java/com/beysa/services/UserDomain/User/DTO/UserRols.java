package com.beysa.services.UserDomain.User.DTO;

import java.util.List;

import com.beysa.services.UserDomain.Rol.Rol;
import com.beysa.services.UserDomain.User.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRols {
    private UserEntity user;
    private List<Rol> rols;
}

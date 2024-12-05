package com.beysa.services.UserDomain.User.DTO;

import java.util.List;

import com.beysa.services.UserDomain.Rol.Rol;
import com.beysa.services.UserDomain.UserPermissions.DTO.UserPermissionsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChargeData {
    UserSend user;
    Rol rol;
    List<UserPermissionsDto> permissions;
}

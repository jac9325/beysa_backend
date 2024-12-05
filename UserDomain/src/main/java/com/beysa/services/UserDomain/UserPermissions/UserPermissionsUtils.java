package com.beysa.services.UserDomain.UserPermissions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.UserPermissions.DTO.UserPermissionsDto;

@Component
public class UserPermissionsUtils {
     public UserPermissionsDto convertStaffDto(UserPermissions permissions) {
        UserPermissionsDto res = new UserPermissionsDto();

        res.setIdUserPermissions(permissions.getIdUserPermissions());
        res.setIdPermissions(permissions.getIdUserPermissions());
        res.setIdUser(permissions.getUser().getIdUser());
        res.setStatus(permissions.getStatus());
        return res;
    }

    public List<UserPermissionsDto> convertListPermissionsDto(List<UserPermissions> list) {
        return list.stream()
                        .map(this::convertStaffDto)
                        .collect(Collectors.toList());
    }
}

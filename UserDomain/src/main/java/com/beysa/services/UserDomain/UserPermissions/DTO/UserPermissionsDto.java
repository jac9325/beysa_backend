package com.beysa.services.UserDomain.UserPermissions.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionsDto {
    private Long idUserPermissions;
    private Long idUser;
    private Long idPermissions;
    private Integer status;
}

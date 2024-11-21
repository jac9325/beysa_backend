package com.beysa.services.UserDomain.RolePermissions;

import com.beysa.services.UserDomain.RolePermissions.DTO.RolePermissionsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RolePermissionsUtils {
    public RolePermissionsDto convertRolePermissionsDto(RolePermissions entity) {
        if (entity == null) return null;
        RolePermissionsDto dto = new RolePermissionsDto();
        dto.setIdRolePermissions(entity.getIdRolePermissions());
        dto.setIdRol(entity.getRol().getId_rol());
        dto.setIdPermissions(entity.getPermissions().getId_permissions());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<RolePermissionsDto> convertListRolePermissionsDto(List<RolePermissions> entities) {
        return entities.stream()
                .map(this::convertRolePermissionsDto)
                .collect(Collectors.toList());
    }
}

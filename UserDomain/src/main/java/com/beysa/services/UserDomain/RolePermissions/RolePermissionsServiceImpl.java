package com.beysa.services.UserDomain.RolePermissions;

import com.beysa.services.UserDomain.RolePermissions.DTO.RolePermissionsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionsServiceImpl implements RolePermissionsService {

    private final RolePermissionsRepository rolePermissionsRepository;
    private final RolePermissionsUtils rolePermissionsUtils;

    @Transactional(readOnly = true)
    @Override
    public List<RolePermissionsDto> getRolePermissionsByIdRole(Long idRole) {
        try {
            List<RolePermissions> rolePermissions = rolePermissionsRepository.findByIdRole(idRole);
            if (rolePermissions.isEmpty()) {
                throw new RuntimeException("No se encontraron registros de Rol-Permisos en la base de datos.");
            }
            return rolePermissionsUtils.convertListRolePermissionsDto(rolePermissions);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    
}

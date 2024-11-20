package com.beysa.services.UserDomain.Permissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionsServiceImpl implements PermissionsService{
    @Autowired
    PermissionsRepository permissionsRepository;

    @Transactional(readOnly = true)
    @Override
    public PermissionsEntity getPermissionById(Long idPermission){
        try {
            PermissionsEntity currentPermission = permissionsRepository.findById(idPermission).orElse(null);
            if (currentPermission == null){
                throw new RuntimeException("Ha ocurrido un error");
            }
            return currentPermission;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

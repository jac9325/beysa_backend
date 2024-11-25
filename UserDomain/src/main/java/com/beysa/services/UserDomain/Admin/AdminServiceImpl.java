package com.beysa.services.UserDomain.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.Admin.DTO.AdminDtos;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepository;

    private final AdminUtils adminUtils;

    public AdminServiceImpl(AdminUtils adminUtils){
        this.adminUtils = adminUtils;
    }

    @Transactional
    @Override
    public AdminDtos saveAdmin(AdminEntity admin){
        try {
            AdminEntity currentAdmin = adminRepository.save(admin);
            AdminDtos response = adminUtils.convertAdminDtos(currentAdmin);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Boolean updateAdmin(AdminDtos newAdmin){
        try {
            AdminEntity oldAdminEntity = adminRepository.findById(newAdmin.getIdAdmin()).orElse(null);
            if (oldAdminEntity == null){
                throw new RuntimeException("Ha ocurrido un error la obtener el Administrador");
            }
            oldAdminEntity.setBranchManager(newAdmin.getBranchManager());
            oldAdminEntity.setUpdateAd(newAdmin.getUpdateAd());
            adminRepository.save(oldAdminEntity);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public AdminDtos getAdminById(Long idAdmin){
        return adminRepository.findById(idAdmin)
                .map(adminUtils::convertAdminDtos)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado por el id: " + idAdmin));
    }
}

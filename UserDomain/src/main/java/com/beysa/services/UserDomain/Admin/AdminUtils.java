package com.beysa.services.UserDomain.Admin;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Admin.DTO.AdminDtos;

@Component
public class AdminUtils {
    public AdminDtos converAdminDtos(Admin admin) {
        AdminDtos dto = new AdminDtos();
        dto.setIdAdmin(admin.getIdAdmin());
        dto.setIdStaff(admin.getStaff().getIdStaff());
        dto.setBranchManager(admin.getBranchManager());
        dto.setTypeAdmin(admin.getTypeAdmin());
        dto.setCreateAd(admin.getCreateAd());
        dto.setUpdateAd(admin.getUpdateAd());
        dto.setStatus(admin.getStatus());
        return dto;
    }

    public List<AdminDtos> toDtoListAdminDtos(List<Admin> adminList) {
        return adminList.stream()
                        .map(this::converAdminDtos)
                        .collect(Collectors.toList());
    }
}

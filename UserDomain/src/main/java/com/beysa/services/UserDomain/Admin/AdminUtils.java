package com.beysa.services.UserDomain.Admin;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Admin.DTO.AdminDtos;

@Component
public class AdminUtils {
    public AdminDtos convertAdminDtos(AdminEntity admin) {
        AdminDtos dto = new AdminDtos();
        dto.setIdAdmin(admin.getIdAdmin());
        dto.setIdStaff(admin.getStaff().getIdStaff());
        dto.setBranchManager(admin.getBranchManager());
        dto.setTypeAdmin(admin.getTypeAdmin());
        dto.setCreateAd(admin.getCreateAd());
        dto.setUpdateAd(admin.getUpdateAd());
        dto.setStatus(admin.getStatus());
        dto.setSignature(admin.getSignature());
        dto.setSloganAdmin(admin.getSloganAdmin());
        return dto;
    }

    public List<AdminDtos> convertListAdminDtos(List<AdminEntity> adminList) {
        return adminList.stream()
                        .map(this::convertAdminDtos)
                        .collect(Collectors.toList());
    }
}

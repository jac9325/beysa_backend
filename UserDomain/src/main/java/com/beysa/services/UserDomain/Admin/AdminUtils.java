package com.beysa.services.UserDomain.Admin;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.beysa.services.UserDomain.Admin.DTO.AdminDto;

@Component
public class AdminUtils {
    public AdminDto convertAdminDto(AdminEntity admin) {
        AdminDto dto = new AdminDto();
        dto.setIdAdmin(admin.getIdAdmin());
        dto.setIdStaff(admin.getStaff().getIdStaff());
        dto.setBranchManager(admin.getBranchManager());
        dto.setTypeAdmin(admin.getTypeAdmin());
        dto.setCreateAd(admin.getCreateAd());
        dto.setUpdateAd(admin.getUpdateAd());
        dto.setStatus(admin.getStatus());
        return dto;
    }

    public List<AdminDto> toDtoListAdminDto(List<AdminEntity> adminList) {
        return adminList.stream()
                        .map(this::convertAdminDto)
                        .collect(Collectors.toList());
    }
}

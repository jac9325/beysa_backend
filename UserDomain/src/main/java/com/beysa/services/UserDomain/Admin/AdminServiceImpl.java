package com.beysa.services.UserDomain.Admin;

import com.beysa.services.UserDomain.Admin.DTO.AdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepository;

    private final AdminUtils adminUtils;

    @Transactional
    @Override
    public AdminDto saveAdmin(AdminEntity admin){
        try {
            AdminEntity currentAdmin = adminRepository.save(admin);
            AdminDto response = adminUtils.convertAdminDto(currentAdmin);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public AdminDto getAdminById(Long idAdmin){
        return adminRepository.findById(idAdmin)
                .map(adminUtils::convertAdminDto)
                .orElseThrow(() -> new RuntimeException("Admin not found for id: " + idAdmin));
    }
}

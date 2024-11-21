package com.beysa.services.UserDomain.Admin;

import com.beysa.services.UserDomain.Admin.DTO.AdminDto;

public interface AdminService {
    AdminDto saveAdmin(AdminEntity admin);
    AdminDto getAdminById(Long idAdmin);
}

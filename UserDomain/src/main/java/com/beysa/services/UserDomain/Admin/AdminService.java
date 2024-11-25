package com.beysa.services.UserDomain.Admin;

import com.beysa.services.UserDomain.Admin.DTO.AdminDtos;

public interface AdminService {
    AdminDtos saveAdmin(AdminEntity admin);
    Boolean updateAdmin(AdminDtos newAdmin);
}

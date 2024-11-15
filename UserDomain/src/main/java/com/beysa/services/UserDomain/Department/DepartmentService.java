package com.beysa.services.UserDomain.Department;

import com.beysa.services.UserDomain.Department.DTO.DepartmentDto;
import java.util.List;

public interface DepartmentService {
    DepartmentDto getDepartmentById(Long idDepartment);
    List<DepartmentDto> getAllDepartment();
}

package com.beysa.services.UserDomain.Department;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Department.DTO.DepartmentDto;

@Component
public class DepartmentUtils {
    public DepartmentDto convertDepartmentDto(Department department){
        DepartmentDto response = new DepartmentDto();
        response.setIdDepartment(department.getIdDepartment());
        response.setName(department.getName());
        response.setIdCountry(department.getCountry().getIdCountry());
        response.setStatus(department.getStatus());
        return response;
    }
    
    public List<DepartmentDto> convertListDepartmentDto(List<Department> listDepartment){
        return listDepartment.stream()
                .map(this::convertDepartmentDto)
                .collect(Collectors.toList());

    }
}

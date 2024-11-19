package com.beysa.services.UserDomain.Department;

import com.beysa.services.UserDomain.Department.DTO.DepartmentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final DepartmentUtils departmentUtils;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentUtils departmentUtils){
        this.departmentRepository = departmentRepository;
        this.departmentUtils = departmentUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public Department getDepartmentById(Long idDepartment){
        return departmentRepository.findById(idDepartment)
                .orElseThrow(() -> new RuntimeException("Department not found for id: " + idDepartment));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepartmentDto> getAllDepartment(){
        List<Department> listDepartment = departmentRepository.findAll();
        if(listDepartment.isEmpty()){
            throw new RuntimeException("No Department records found in the database");
        }
        return departmentUtils.convertListDepartmentDto(listDepartment);
    }
}

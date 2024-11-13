package com.beysa.services.UserDomain.Department.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long idDepartment;
    private String name;
    private Long idCountry;
    private Integer status; 
}

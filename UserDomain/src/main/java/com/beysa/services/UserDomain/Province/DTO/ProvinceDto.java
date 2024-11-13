package com.beysa.services.UserDomain.Province.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvinceDto {
    private Long idProvince;
    private String name;
    private Long idDepartment;
    private Integer status;
}

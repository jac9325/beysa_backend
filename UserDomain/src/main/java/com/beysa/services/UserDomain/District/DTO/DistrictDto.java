package com.beysa.services.UserDomain.District.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto {
    private Long idDistrict;
    private String name;
    private Long idProvince;
    private Integer status;
}

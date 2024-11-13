package com.beysa.services.UserDomain.Province;
import java.util.List;
import java.util.stream.Collectors;

import com.beysa.services.UserDomain.Province.DTO.ProvinceDto;

public class ProvinceUtils {
    public ProvinceDto converProvinceDto(Province province){
        ProvinceDto response = new ProvinceDto();
        response.setIdProvince(province.getIdProvince());
        response.setName(province.getName());
        response.setIdDepartment(province.getDepartment().getIdDepartment());
        response.setStatus(province.getStatus());
        return response;
    }
    
    public List<ProvinceDto> convertListProvinceDto(List<Province> listProvince){
        return listProvince.stream()
                .map(this::converProvinceDto)
                .collect(Collectors.toList());

    }
}

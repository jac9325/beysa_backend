package com.beysa.services.UserDomain.District;

import java.util.List;
import java.util.stream.Collectors;

import com.beysa.services.UserDomain.District.DTO.DistrictDto;

public class DistrictUtils {
    public DistrictDto convertDepartamentDto(District district){
        DistrictDto response = new DistrictDto();
        response.setIdDistrict(district.getIdDistrict());
        response.setName(district.getName());
        response.setIdProvince(district.getProvince().getIdProvince());
        response.setStatus(district.getStatus());
        return response;
    }
    
    public List<DistrictDto> convertListDepartmentDto(List<District> listDistricts){
        return listDistricts.stream()
                .map(this::convertDepartamentDto)
                .collect(Collectors.toList());

    }
}
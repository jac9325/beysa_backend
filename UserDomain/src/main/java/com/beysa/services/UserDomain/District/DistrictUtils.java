package com.beysa.services.UserDomain.District;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.District.DTO.DistrictDto;

@Component
public class DistrictUtils {
    public DistrictDto convertDistrictDto(District district){
        DistrictDto response = new DistrictDto();
        response.setIdDistrict(district.getIdDistrict());
        response.setName(district.getName());
        response.setIdProvince(district.getProvince().getIdProvince());
        response.setStatus(district.getStatus());
        return response;
    }
    
    public List<DistrictDto> convertListDistrictDto(List<District> listDistricts){
        return listDistricts.stream()
                .map(this::convertDistrictDto)
                .collect(Collectors.toList());

    }
}

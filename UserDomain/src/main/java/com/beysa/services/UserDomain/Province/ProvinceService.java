package com.beysa.services.UserDomain.Province;

import com.beysa.services.UserDomain.Province.DTO.ProvinceDto;

import java.util.List;

public interface ProvinceService {
    ProvinceDto getProvinceById(Long idProvince);
    List<ProvinceDto> getAllProvince();
}

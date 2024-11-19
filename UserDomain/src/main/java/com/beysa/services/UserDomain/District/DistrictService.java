package com.beysa.services.UserDomain.District;

import com.beysa.services.UserDomain.District.DTO.DistrictDto;

import java.util.List;

public interface DistrictService {
    DistrictDto getDistrictById(Long idDistrict);
    List<DistrictDto> getAllDistrict();
    District getDistrictByIdEntity(Long idDistrict);
}

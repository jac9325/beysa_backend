package com.beysa.services.UserDomain.GeographicalLocation.DTO;

import java.util.List;

import com.beysa.services.UserDomain.Country.Country;
import com.beysa.services.UserDomain.Department.DTO.DepartmentDto;
import com.beysa.services.UserDomain.District.DTO.DistrictDto;
import com.beysa.services.UserDomain.IdentityDocument.IdentityDocument;
import com.beysa.services.UserDomain.Province.DTO.ProvinceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericData {
    List<Country> countries;
    List<DepartmentDto> departments;
    List<ProvinceDto> provinces;
    List<DistrictDto> districts;
    List<IdentityDocument> identityDocuments;
}

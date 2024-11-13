package com.beysa.services.UserDomain.GeographicalLocation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeographicalLocationDto {
    private Long idGeographicalLocation;
    private Long idCountry;
    private Long idDepartment;
    private Long idProvince;
    private Long idDistrict;
    private Integer status;
}

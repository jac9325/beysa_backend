package com.beysa.services.UserDomain.GeographicalLocation;

import java.util.List;
import java.util.stream.Collectors;

import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;

public class GeographicalLocationUtils {
    public GeographicalLocationDto convertGeographicalLocationDto(GeographicalLocation geo){
        GeographicalLocationDto response = new GeographicalLocationDto();
        response.setIdGeographicalLocation(geo.getIdGeographicalLocation());
        response.setIdCountry(geo.getCountry().getIdCountry());
        response.setIdProvince(geo.getProvince().getIdProvince());
        response.setIdDistrict(geo.getDistrict().getIdDistrict());
        response.setStatus(geo.getStatus());
        return response;
    }
    
    public List<GeographicalLocationDto> convertListGeographicalLocationDto(List<GeographicalLocation> listGeo){
        return listGeo.stream()
                .map(this::convertGeographicalLocationDto)
                .collect(Collectors.toList());

    }
}

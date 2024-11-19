package com.beysa.services.UserDomain.GeographicalLocation;

import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;

public interface GeographicalLocationService {
    GeographicalLocationDto getGeographicalLocationById(Long idGeographicalLocation);
    GeographicalLocationDto saveGeoGraphical(GeographicalLocation geo);
}

package com.beysa.services.UserDomain.GeographicalLocation;

import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;

import java.util.List;

public interface GeographicalLocationService {
    GeographicalLocationDto getGeographicalLocationById(Long idGeographicalLocation);
}

package com.beysa.services.UserDomain.GeographicalLocation;

import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GeographicalLocationServiceImpl implements GeographicalLocationService{

    private final GeographicalLocationRepository geographicalLocationRepository;
    private final GeographicalLocationUtils geographicalLocationUtils;

    public GeographicalLocationServiceImpl(GeographicalLocationRepository geographicalLocationRepository, GeographicalLocationUtils geographicalLocationUtils){
        this.geographicalLocationRepository = geographicalLocationRepository;
        this.geographicalLocationUtils = geographicalLocationUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public GeographicalLocationDto getGeographicalLocationById(Long idGeographicalLocation){
        return geographicalLocationRepository.findById(idGeographicalLocation)
                .map(geographicalLocationUtils::convertGeographicalLocationDto)
                .orElseThrow(() -> new RuntimeException("GeographicalLocation not found for id: " + idGeographicalLocation));
    }

    @Transactional
    @Override
    public GeographicalLocationDto saveGeoGraphical(GeographicalLocation geo){
        try {
            geo = geographicalLocationRepository.save(geo);
            GeographicalLocationDto currentResponse = geographicalLocationUtils.convertGeographicalLocationDto(geo);
            return currentResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

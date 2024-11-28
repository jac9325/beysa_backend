package com.beysa.services.UserDomain.GeographicalLocation;

import com.beysa.services.UserDomain.Country.CountryService;
import com.beysa.services.UserDomain.Department.DepartmentService;
import com.beysa.services.UserDomain.District.DistrictService;
import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;
import com.beysa.services.UserDomain.Province.ProvinceService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GeographicalLocationServiceImpl implements GeographicalLocationService{

    private final GeographicalLocationRepository geographicalLocationRepository;
    private final GeographicalLocationUtils geographicalLocationUtils;
    private final CountryService countryService;
    private final DepartmentService departmentService;
    private final ProvinceService provinceService;
    private final DistrictService districtService;

    public GeographicalLocationServiceImpl(
        GeographicalLocationRepository geographicalLocationRepository,
        GeographicalLocationUtils geographicalLocationUtils,
        CountryService countryService,
        DepartmentService departmentService,
        ProvinceService provinceService,
        DistrictService districtService
    ){
        this.geographicalLocationRepository = geographicalLocationRepository;
        this.geographicalLocationUtils = geographicalLocationUtils;
        this.countryService = countryService;
        this.departmentService = departmentService;
        this.provinceService = provinceService;
        this.districtService = districtService;
    }

    @Transactional(readOnly = true)
    @Override
    public GeographicalLocationDto getGeographicalLocationById(Long idGeographicalLocation){
        return geographicalLocationRepository.findById(idGeographicalLocation)
                .map(geographicalLocationUtils::convertGeographicalLocationDto)
                .orElseThrow(() -> new RuntimeException("Ubicación geográfica no encontrado por el id: " + idGeographicalLocation));
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

    @Transactional(readOnly = true)
    @Override
    public GeographicalLocation getGeographicalLocationByIdEntity(Long idGeographicalLocation){
        return geographicalLocationRepository.findById(idGeographicalLocation)
                .orElseThrow(() -> new RuntimeException("GeographicalLocation not found for id: " + idGeographicalLocation));
    }

}

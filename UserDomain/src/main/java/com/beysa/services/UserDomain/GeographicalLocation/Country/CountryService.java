package com.beysa.services.UserDomain.GeographicalLocation.Country;

import java.util.List;

public interface CountryService {
    CountryEntity getCountryById(Long idCountry);
    List<CountryEntity> getAllCountry();
}

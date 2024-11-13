package com.beysa.services.UserDomain.Country;

import java.util.List;

public interface CountryService {
    Country getCountryById(Long idCountry);
    List<Country> getAllCountry();
}

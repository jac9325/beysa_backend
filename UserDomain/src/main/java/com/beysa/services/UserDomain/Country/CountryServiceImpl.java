package com.beysa.services.UserDomain.Country;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Country getCountryById(Long idCountry){
        return countryRepository.findById(idCountry)
                .orElseThrow(() -> new RuntimeException("Country not found for id: " + idCountry));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Country> getAllCountry(){
        List<Country> countries = countryRepository.findAll();
        if (countries.isEmpty()) {
            throw new RuntimeException("No countries found in the database.");
        }
        return countries;
    }
}

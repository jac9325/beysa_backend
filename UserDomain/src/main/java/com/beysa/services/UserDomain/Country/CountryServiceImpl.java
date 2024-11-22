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
                .orElseThrow(() -> new RuntimeException("Pais no encontrado por el id: " + idCountry));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Country> getAllCountry(){
        List<Country> countries = countryRepository.findAll();
        if (countries.isEmpty()) {
            throw new RuntimeException("No se encontraron registros de Pais en la base de datos.");
        }
        return countries;
    }
}

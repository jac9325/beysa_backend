package com.beysa.services.UserDomain.GeographicalLocation.Country;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getCountryById/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable(value = "id") Long id){
        try{
            CountryEntity country = countryService.getCountryById(id);
            return response.ok(codes.ok(), messages.ok(), country, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllCountry")
    public ResponseEntity<?> getAllCountry(){
        try{
            List<CountryEntity> listCountry = countryService.getAllCountry();
            return response.ok(codes.ok(), messages.ok(), listCountry, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

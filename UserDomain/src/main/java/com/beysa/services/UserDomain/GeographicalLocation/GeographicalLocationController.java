package com.beysa.services.UserDomain.GeographicalLocation;

import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;
import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/geographical/location")
public class GeographicalLocationController {
    @Autowired
    private GeographicalLocationService geographicalLocationService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getGeographicalLocationById/{id}")
    public ResponseEntity<?> getGeographicalLocationById(@PathVariable(value = "id") Long id){
        try{
            GeographicalLocationDto geographicalLocation = geographicalLocationService.getGeographicalLocationById(id);
            return response.ok(codes.ok(), messages.ok(), geographicalLocation, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

package com.beysa.services.UserDomain.Clinic;

import com.beysa.services.UserDomain.Clinic.DTO.ClinicAndConfigurationDto;
import com.beysa.services.UserDomain.Clinic.DTO.ClinicDto;
import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/clinic")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getClinicById/{id}")
    public ResponseEntity<?> getClinicById(@PathVariable(value = "id") Long id){
        try{
            ClinicDto clinic = clinicService.getClinicById(id);
            return response.ok(codes.ok(), messages.ok(), clinic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("getClinicAndConfigurationById/{id}")
    public ResponseEntity<?> getClinicAndConfigurationById(@PathVariable(value = "id") Long id){
        try {
            ClinicAndConfigurationDto clinicAndConfiguration = clinicService.getClinicAndConfigurationById(id);
            return response.ok(codes.ok(), messages.ok(), clinicAndConfiguration, null);
        } catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}
package com.beysa.services.UserDomain.ClinicConfiguration;

import com.beysa.services.UserDomain.ClinicConfiguration.DTO.ClinicConfigurationDto;
import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/clinicConfiguration")
public class ClinicConfigurationController {
    @Autowired
    private ClinicConfigurationService clinicConfigurationService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getClinicConfigurationById/{id}")
    public ResponseEntity<?> getClinicConfigurationById(@PathVariable(value = "id") Long id){
        try{
            ClinicConfigurationDto clinicConfiguration = clinicConfigurationService.getClinicConfigurationById(id);
            return response.ok(codes.ok(), messages.ok(), clinicConfiguration, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getClinicConfigurationByIdClinic/{id}")
    public ResponseEntity<?> getClinicConfigurationByIdClinic(@PathVariable(value = "id") Long id){
        try{
            ClinicConfigurationDto clinicConfiguration = clinicConfigurationService.getClinicConfigurationByIdClinic(id);
            return response.ok(codes.ok(), messages.ok(), clinicConfiguration, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

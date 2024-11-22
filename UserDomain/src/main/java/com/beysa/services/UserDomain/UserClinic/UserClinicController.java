package com.beysa.services.UserDomain.UserClinic;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.UserClinic.DTO.UserClinicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/user/clinic")
public class UserClinicController {
    @Autowired
    private UserClinicService userClinicService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getUserClinicById/{id}")
    public ResponseEntity<?> getUserClinicById(@PathVariable(value = "id") Long id){
        try{
            UserClinicDto userClinic = userClinicService.getUserClinicById(id);
            return response.ok(codes.ok(), messages.ok(), userClinic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getUserClinicByIdClinic/{id}")
    public ResponseEntity<?> getUserClinicByIdClinic(@PathVariable(value = "id") Long id){
        try{
            List<UserClinicDto> userClinic = userClinicService.getUserClinicByIdClinic(id);
            return response.ok(codes.ok(), messages.ok(), userClinic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

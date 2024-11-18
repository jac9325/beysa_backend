package com.beysa.services.UserDomain.Speciality;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/speciality")

public class SpecialityController {
    @Autowired
    private SpecialityService specialityService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();


    @GetMapping("/getAllSpeciality")
    public ResponseEntity<?> getAllSpeciality(){
        try{
            List<Speciality> listSpeciality = specialityService.getAllSpeciality();
            return response.ok(codes.ok(), messages.ok(), listSpeciality, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
    @PostMapping("/addSpecialities")
    public ResponseEntity<?> addSpecialities(@RequestBody List<Speciality> specialities){
        try{
            List<Speciality> savedSpecialities = specialityService.addSpecialities(specialities);
            return response.ok(codes.ok(), messages.ok(), savedSpecialities, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

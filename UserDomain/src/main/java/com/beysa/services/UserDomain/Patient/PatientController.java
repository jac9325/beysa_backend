package com.beysa.services.UserDomain.Patient;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.Patient.DTO.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getPatientById/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable(value = "id") Long id){
        try{
            PatientDto patient = patientService.getPatientById(id);
            return response.ok(codes.ok(), messages.ok(), patient, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllPatient")
    public ResponseEntity<?> getAllPatient(){
        try{
            List<PatientDto> listPatient = patientService.getAllPatient();
            return response.ok(codes.ok(), messages.ok(), listPatient, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

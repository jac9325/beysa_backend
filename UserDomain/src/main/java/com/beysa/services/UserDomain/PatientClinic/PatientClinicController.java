package com.beysa.services.UserDomain.PatientClinic;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.PatientClinic.DTO.PatientClinicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/patient/clinic")
public class PatientClinicController {
    @Autowired
    private PatientClinicService patientClinicService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getPatientClinicById/{id}")
    public ResponseEntity<?> getPatientClinicById(@PathVariable(value = "id") Long id){
        try{
            PatientClinicDto patientClinic = patientClinicService.getPatientClinicById(id);
            return response.ok(codes.ok(), messages.ok(), patientClinic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getPatientClinicByIdClinic/{id}")
    public ResponseEntity<?> getPatientClinicByIdClinic(@PathVariable(value = "id") Long id){
        try{
            List<PatientClinicDto> patientClinic = patientClinicService.getPatientClinicByIdClinic(id);
            return response.ok(codes.ok(), messages.ok(), patientClinic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

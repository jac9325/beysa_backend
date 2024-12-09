package com.beysa.services.UserDomain.Treatment;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.Treatment.DTO.TreatmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/treatment")
public class TreatmentController {
    @Autowired
    private TreatmentService treatmentService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getTreatmentById/{id}")
    public ResponseEntity<?> getTreatmentById(@PathVariable(value = "id") Long id){
        try{
            TreatmentDto treatment = treatmentService.getTreatmentById(id);
            return response.ok(codes.ok(), messages.ok(), treatment, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllTreatment")
    public ResponseEntity<?> getAllTreatment(){
        try{
            List<TreatmentDto> listTreatment = treatmentService.getAllTreatment();
            return response.ok(codes.ok(), messages.ok(), listTreatment, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getTreatmentByIdClinic/{id}")
    public ResponseEntity<?> getTreatmentByIdClinic(@PathVariable(value = "id") Long id){
        try{
            List<TreatmentDto> treatment = treatmentService.getTreatmentByIdClinic(id);
            return response.ok(codes.ok(), messages.ok(), treatment, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

package com.beysa.services.UserDomain.Medic;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/medic")
public class MedicController {
    @Autowired
    private MedicService medicService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getMedicById/{id}")
    public ResponseEntity<?> getMedicById(@PathVariable(value = "id") Long id){
        try{
            MedicDto medic = medicService.getMedicById(id);
            return response.ok(codes.ok(), messages.ok(), medic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllMedic")
    public ResponseEntity<?> getAllMedic(){
        try{
            List<MedicDto> listMedic = medicService.getAllMedic();
            return response.ok(codes.ok(), messages.ok(), listMedic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

}

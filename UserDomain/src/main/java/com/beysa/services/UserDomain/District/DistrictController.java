package com.beysa.services.UserDomain.District;

import com.beysa.services.UserDomain.District.DTO.DistrictDto;
import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getDistrictById/{id}")
    public ResponseEntity<?> getDistrictById(@PathVariable(value = "id") Long id){
        try{
            DistrictDto district = districtService.getDistrictById(id);
            return response.ok(codes.ok(), messages.ok(), district, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllDistrict")
    public ResponseEntity<?> getAllDistrict(){
        try{
            List<DistrictDto> listDistrict = districtService.getAllDistrict();
            return response.ok(codes.ok(), messages.ok(), listDistrict, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

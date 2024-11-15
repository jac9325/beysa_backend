package com.beysa.services.UserDomain.Province;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.Province.DTO.ProvinceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getProvinceById/{id}")
    public ResponseEntity<?> getProvinceById(@PathVariable(value = "id") Long id){
        try{
            ProvinceDto province = provinceService.getProvinceById(id);
            return response.ok(codes.ok(), messages.ok(), province, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllProvince")
    public ResponseEntity<?> getAllProvince(){
        try{
            List<ProvinceDto> listProvince = provinceService.getAllProvince();
            return response.ok(codes.ok(), messages.ok(), listProvince, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

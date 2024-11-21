package com.beysa.services.UserDomain.CashRegister;

import com.beysa.services.UserDomain.CashRegister.DTO.CashRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/cash/register")
public class CashRegisterController {
    @Autowired
    private CashRegisterService cashRegisterService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getCashRegisterById/{id}")
    public ResponseEntity<?> getCashRegisterById(@PathVariable(value = "id") Long id){
        try{
            CashRegisterDto cashRegister = cashRegisterService.getCashRegisterById(id);
            return response.ok(codes.ok(), messages.ok(), cashRegister, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

}

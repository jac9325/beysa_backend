package com.beysa.services.UserDomain.Income;

import com.beysa.services.UserDomain.Income.DTO.IncomeDto;
import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getIncomeById/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable(value = "id") Long id){
        try{
            IncomeDto income = incomeService.getIncomeById(id);
            return response.ok(codes.ok(), messages.ok(), income, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    // @GetMapping("/getIncomeByIdCashSession/{id}")
    // public ResponseEntity<?> getIncomeByIdCashSession(@PathVariable(value = "id") Long id){
    //     try{
    //         List<IncomeDto> income = incomeService.getIncomeByIdCashSession(id);
    //         return response.ok(codes.ok(), messages.ok(), income, null);
    //     }catch (Exception e){
    //         return response.error(codes.error(), messages.error() + e.getMessage(), null);
    //     }
    // }
}

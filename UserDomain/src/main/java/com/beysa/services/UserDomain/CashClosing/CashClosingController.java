package com.beysa.services.UserDomain.CashClosing;

import com.beysa.services.UserDomain.CashClosing.DTO.CashClosingDto;
import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/cashClosing")
public class CashClosingController {
    @Autowired
    private CashClosingService cashClosingService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getCashClosingById/{id}")
    public ResponseEntity<?> getCashClosingById(@PathVariable(value = "id") Long id){
        try{
            CashClosingDto cashClosing = cashClosingService.getCashClosingById(id);
            return response.ok(codes.ok(), messages.ok(), cashClosing, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    // @GetMapping("/getCashClosingByIdCashSession/{id}")
    // public ResponseEntity<?> getCashClosingByIdCashSession(@PathVariable(value = "id") Long id){
    //     try{
    //         CashClosingDto cashClosing = cashClosingService.getCashClosingByIdCashSession(id);
    //         return response.ok(codes.ok(), messages.ok(), cashClosing, null);
    //     }catch (Exception e){
    //         return response.error(codes.error(), messages.error() + e.getMessage(), null);
    //     }
    // }
}

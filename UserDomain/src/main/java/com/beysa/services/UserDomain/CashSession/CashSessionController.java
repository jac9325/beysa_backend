package com.beysa.services.UserDomain.CashSession;

import com.beysa.services.UserDomain.CashSession.DTO.CashSessionDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/cash/session")
public class CashSessionController {
    @Autowired
    private CashSessionService cashSessionService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getCashSessionById/{id}")
    public ResponseEntity<?> getCashSessionById(@PathVariable(value = "id") Long id){
        try{
            CashSessionDtos cashSession = cashSessionService.getCashSessionById(id);
            return response.ok(codes.ok(), messages.ok(), cashSession, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getCashSessionByIdCashRegister/{id}")
    public ResponseEntity<?> getCashSessionByIdCashRegister(@PathVariable(value = "id") Long id){
        try{
            List<CashSessionDtos> cashSession = cashSessionService.getCashSessionByIdCashRegister(id);
            return response.ok(codes.ok(), messages.ok(), cashSession, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

}

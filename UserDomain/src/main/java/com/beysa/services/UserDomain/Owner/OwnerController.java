package com.beysa.services.UserDomain.Owner;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.Owner.DTO.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getOwnerById/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable(value = "id") Long id){
        try{
            OwnerDto owner = ownerService.getOwnerById(id);
            return response.ok(codes.ok(), messages.ok(), owner, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getOwnerByIdUser/{id}")
    public ResponseEntity<?> getOwnerByIdUser(@PathVariable(value = "id") Long id){
        try{
            OwnerDto owner = ownerService.getOwnerByIdUser(id);
            return response.ok(codes.ok(), messages.ok(), owner, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

package com.beysa.services.UserDomain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.User.DTO.UserRols;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @Autowired
    public UserService userService;
    
    // @PostMapping("/create/user/rols")
    // public ResponseEntity<?> createUsuarioRoles(@RequestBody UserRols request) {
    //     try {
    //         Boolean result = userService.createUserAll(request.getUser(), request.getRols());
    //         if (result == null) {
    //             return response.error(codes.error(), messages.error(), null);
    //         }
    //         return response.success(codes.created(), messages.created(), result, null);
    //     } catch (Exception e) {
    //         return response.error(codes.error(), messages.error() + e.getMessage(), null);
    //     }
    // }
    
}

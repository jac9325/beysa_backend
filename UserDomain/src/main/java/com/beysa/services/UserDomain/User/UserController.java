package com.beysa.services.UserDomain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @Autowired
    public UserService userService;
    
    @PutMapping("/change/password")
    public ResponseEntity<?> createUsuarioRoles(@RequestBody UserEntity newUser) {
        try {
            Boolean result = userService.changePasswordStaff(newUser);
            if (result == null) {
                return response.error(codes.error(), messages.error(), null);
            }
            return response.ok(codes.ok(), messages.ok(), result, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

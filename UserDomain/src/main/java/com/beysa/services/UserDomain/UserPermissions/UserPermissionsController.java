package com.beysa.services.UserDomain.UserPermissions;

import java.security.Permission;
import java.util.List;

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
import com.beysa.services.UserDomain.UserPermissions.DTO.UserPermissionsDto;
import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/v1/user/permissions")
public class UserPermissionsController {
    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @Autowired
    public UserPermissionsService userPermissionsService;
    
    @PutMapping("/delete/permissions")
    public ResponseEntity<?> createUsuarioRoles(@RequestBody List<UserPermissionsDto> list) {
        try {
            Boolean result = userPermissionsService.deteleUserPermisisions(list);
            if (result == null) {
                return response.error(codes.error(), messages.error(), null);
            }
            return response.ok(codes.ok(), messages.ok(), result, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PostMapping("/add/permissions")
    public ResponseEntity<?> addPermissions(@RequestBody List<UserPermissionsDto> list) {
        try {
            Boolean result = userPermissionsService.addUserPermisisions(list);
            if (result == null) {
                return response.error(codes.error(), messages.error(), null);
            }
            return response.success(codes.ok(), messages.ok(), result, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
    
}

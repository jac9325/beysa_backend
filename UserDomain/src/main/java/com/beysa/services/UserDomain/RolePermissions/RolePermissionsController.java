package com.beysa.services.UserDomain.RolePermissions;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.RolePermissions.DTO.RolePermissionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/rolePermissions")
public class RolePermissionsController {
    @Autowired
    private RolePermissionsService rolePermissionsService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();
    
    @GetMapping("/getRolePermissionsByIdRole/{id}")
    public ResponseEntity<?> getRolePermissionsByIdRole(@PathVariable(value = "id") Long id){
        try{
            List<RolePermissionsDto> rolePermissions = rolePermissionsService.getRolePermissionsByIdRole(id);
            return response.ok(codes.ok(), messages.ok(), rolePermissions, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

package com.beysa.services.UserDomain.Rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/v1/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/{idRol}")
    public ResponseEntity<?> getRolById(@PathVariable(value = "idRol") Long idRol) {
        try {
            Rol currentRol = rolService.getRolById(idRol);
            if (currentRol == null) {
                return response.error(codes.notFound(), messages.notFound(), null);
            } else {
                return response.ok(codes.ok(), messages.ok(), currentRol, null);
            }
        } catch (Exception e) {

            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRol() {
        try {
            List<Rol> currentListRol = rolService.getAllRol();
            if (currentListRol == null) {
                return response.error(codes.notFound(), messages.notFound(), null);
            } else {
                return response.ok(codes.ok(), messages.ok(), currentListRol, null);
            }
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

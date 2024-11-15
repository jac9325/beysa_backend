package com.beysa.services.UserDomain.Collaborator;

import com.beysa.services.UserDomain.Collaborator.DTO.CollaboratorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/collaborator")
public class CollaboratorController {
    @Autowired
    private CollaboratorService collaboratorService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getCollaboratorById/{id}")
    public ResponseEntity<?> getCollaboratorById(@PathVariable(value = "id") Long id){
        try{
            CollaboratorDto collaborator = collaboratorService.getCollaboratorById(id);
            return response.ok(codes.ok(), messages.ok(), collaborator, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllCollaborator")
    public ResponseEntity<?> getAllCollaborator(){
        try{
            List<CollaboratorDto> listCollaborator = collaboratorService.getAllCollaborator();
            return response.ok(codes.ok(), messages.ok(), listCollaborator, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

package com.beysa.services.UserDomain.Collaborator;

import com.beysa.services.UserDomain.Collaborator.DTO.CollaboratorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.Shared.ServerImage.StorageService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/collaborator")
public class CollaboratorController {
    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private StorageService storageService;

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

    @PutMapping("/update/collaborator")
    public ResponseEntity<?> getAllCollaborator(@RequestBody CollaboratorDto newCollaborator){
        try{
            Boolean resp = collaboratorService.updateColaborator(newCollaborator);
            return response.ok(codes.ok(), messages.ok(), resp, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PutMapping("/update/signature/collaborator")
    public ResponseEntity<?> updateSignatureAdmin(
        @RequestPart("fileSignature") MultipartFile fileSignature,
        @RequestPart("idCollaborator") String idCollaborator
        ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Long currentIdCollaborator = mapper.readValue(idCollaborator, Long.class);
            String newSignature = storageService.store(fileSignature);
            Boolean resp = collaboratorService.updateSignatureCollaborator(currentIdCollaborator, newSignature);        
            return response.ok(codes.ok(), messages.ok(), resp, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("get/image/collaborator/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new RuntimeException("Filename is null");
        }
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
}

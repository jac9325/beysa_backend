package com.beysa.services.UserDomain.Admin;

import com.beysa.services.UserDomain.Admin.DTO.AdminDtos;

import java.io.IOException;
import java.nio.file.Files;

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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private StorageService storageService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @PutMapping("/update/medic")
    public ResponseEntity<?> updateMedic(@RequestBody AdminDtos newAdmin){
        try {
            Boolean resp = adminService.updateAdmin(newAdmin);
            return response.ok(codes.ok(),messages.ok(), resp, null);
        }catch(Exception e){
            return response.error(codes.error(),messages.error() + e.getMessage(), null);
        }
    }
    @GetMapping("/getAdminById/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable(value = "id") Long id){
        try{
            AdminDtos admin = adminService.getAdminById(id);
            return response.ok(codes.ok(), messages.ok(), admin, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PutMapping("/update/signature/admin")
    public ResponseEntity<?> updateSignatureAdmin(
        @RequestPart("fileSignature") MultipartFile fileSignature,
        @RequestPart("idAdmin") String idAdmin
        ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Long currentIdAdmin = mapper.readValue(idAdmin, Long.class);
            String newSignature = storageService.store(fileSignature);
            Boolean resp = adminService.updateSignatureAdmin(currentIdAdmin, newSignature);        
            return response.ok(codes.ok(), messages.ok(), resp, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("get/image/admin/{filename:.+}")
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

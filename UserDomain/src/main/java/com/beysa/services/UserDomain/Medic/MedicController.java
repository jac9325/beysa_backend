package com.beysa.services.UserDomain.Medic;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;
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
@RequestMapping("api/v1/medic")
public class MedicController {
    @Autowired
    private MedicService medicService;

    @Autowired
    private StorageService storageService;


    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getMedicById/{id}")
    public ResponseEntity<?> getMedicById(@PathVariable(value = "id") Long id){
        try{
            MedicDto medic = medicService.getMedicById(id);
            return response.ok(codes.ok(), messages.ok(), medic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllMedic")
    public ResponseEntity<?> getAllMedic(){
        try{
            List<MedicDto> listMedic = medicService.getAllMedic();
            return response.ok(codes.ok(), messages.ok(), listMedic, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PutMapping("/update/medic")
    public ResponseEntity<?> updateMedic(@RequestBody MedicDto newMedic){
        try {
            Boolean medic = medicService.updateMedic(newMedic);
            return response.ok(codes.ok(),messages.ok(), medic, null);
        }catch(Exception e){
            return response.error(codes.error(),messages.error() + e.getMessage(), null);
        }
    }

    @DeleteMapping("/deleteMedic/{id}")
    public ResponseEntity<?> deleteMedic(@PathVariable(value = "id") Long id) {
        try {
            Boolean result = medicService.deleteMedic(id);
            if (result) {
                return response.ok(codes.ok(), messages.ok(), null, null);
            } else {
                return response.error(codes.notFound(), messages.notFound(), null);}
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PutMapping("/update/signature/medic")
    public ResponseEntity<?> updateSignatureMedic(
        @RequestPart("fileSignature") MultipartFile fileSignature,
        @RequestPart("idMedic") String idMedic
        ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Long currentIdMedic = mapper.readValue(idMedic, Long.class);
            String newSignature = storageService.store(fileSignature);
            Boolean userInstitutionSpecialist = medicService.updateSignatureMedic(currentIdMedic, newSignature);        
            return response.ok(codes.ok(), messages.ok(), userInstitutionSpecialist, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("get/image/medic/{filename:.+}")
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

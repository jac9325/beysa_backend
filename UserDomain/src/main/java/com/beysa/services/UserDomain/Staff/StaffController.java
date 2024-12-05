package com.beysa.services.UserDomain.Staff;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.Shared.ServerImage.StorageService;
import com.beysa.services.UserDomain.Staff.DTO.StaffAdmin;
import com.beysa.services.UserDomain.Staff.DTO.StaffCollaborator;
import com.beysa.services.UserDomain.Staff.DTO.StaffMedic;
import com.beysa.services.UserDomain.Staff.DTO.StaffWithUbication;
import com.beysa.services.UserDomain.User.DTO.UserChargeData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @Autowired
    private StorageService storageService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();


    @PostMapping("/save/staff/medic")
    public ResponseEntity<?> createStaffMedic(@RequestBody StaffMedic request) {
        try {
            StaffMedic result = staffService.registerNewMedic(request);
            if (result == null) {
                return response.error(codes.error(), messages.error(), null);
            }
            return response.success(codes.created(), messages.created(), result, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PostMapping("/save/staff/collaborator")
    public ResponseEntity<?> createStaffCollaborator(@RequestBody StaffCollaborator request) {
        try {
            StaffCollaborator result = staffService.registerNewCollaborator(request);
            if (result == null) {
                return response.error(codes.error(), messages.error(), null);
            }
            return response.success(codes.created(), messages.created(), result, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PostMapping("/save/staff/admin")
    public ResponseEntity<?> createStaffAdmin(@RequestBody StaffAdmin request) {
        try {
            StaffAdmin result = staffService.registerNewAdmin(request);
            if (result == null) {
                return response.error(codes.error(), messages.error(), null);
            }
            return response.success(codes.created(), messages.created(), result, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PutMapping("/update/staff")
    public ResponseEntity<?> createStaffAdmin(@RequestBody StaffWithUbication newStaff) {
        try {
            Boolean result = staffService.updateStaff(newStaff.getStaff(), newStaff.getUbication());
            if (result == null) {
                return response.error(codes.error(), messages.error(), null);
            }
            return response.ok(codes.ok(), messages.ok(), result, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @PutMapping("/update/image/staff")
    public ResponseEntity<?> updateImageStaff(
        @RequestPart("fileImage") MultipartFile fileImage,
        @RequestPart("idStaff") String idStaff
        ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Long currentIdStaff = mapper.readValue(idStaff, Long.class);
            String newPath = storageService.store(fileImage);
            Boolean userInstitutionSpecialist = staffService.updateImageStaff(currentIdStaff, newPath);        
            return response.ok(codes.ok(), messages.ok(), userInstitutionSpecialist, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("get/image/staff/{filename:.+}")
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

    @GetMapping("/get/charge/data/initial/{username}")
    public ResponseEntity<?> createUsuarioRoles(@PathVariable(value="username") String username) {
        try {
            UserChargeData result = staffService.chargeDataUser(username);
            if (result == null) {
                return response.error(codes.error(), messages.error(), null);
            }
            return response.ok(codes.ok(), messages.ok(), result, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

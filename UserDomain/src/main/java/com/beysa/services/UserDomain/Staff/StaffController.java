package com.beysa.services.UserDomain.Staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.Staff.DTO.StaffAdmin;
import com.beysa.services.UserDomain.Staff.DTO.StaffCollaborator;
import com.beysa.services.UserDomain.Staff.DTO.StaffDto;
import com.beysa.services.UserDomain.Staff.DTO.StaffMedic;
import com.beysa.services.UserDomain.Staff.DTO.StaffWithUbication;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

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
}

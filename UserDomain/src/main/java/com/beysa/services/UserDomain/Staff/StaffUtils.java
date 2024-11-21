package com.beysa.services.UserDomain.Staff;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Staff.DTO.StaffDto;

@Component
public class StaffUtils {
     public StaffDto convertStaffDto(Staff staff) {
        StaffDto dto = new StaffDto();
        dto.setIdStaff(staff.getIdStaff());
        dto.setName(staff.getName());
        dto.setLastName(staff.getLastName());
        dto.setDateOfBirth(staff.getDateOfBirth());
        dto.setGender(staff.getGender());
        dto.setIdIdentityDocument(staff.getIdentityDocument().getIdIdentityDocument());
        dto.setDocumentNumber(staff.getDocumentNumber());
        dto.setMobileNumber(staff.getMobileNumber());
        dto.setEmail(staff.getEmail());
        dto.setAddress(staff.getAddress());
        dto.setImage(staff.getImage());
        dto.setDateEntry(staff.getDateEntry());
        dto.setIdGeographicalLocation(staff.getGeographicalLocation().getIdGeographicalLocation());
        dto.setSalary(staff.getSalary());
        dto.setContractType(staff.getContractType());
        dto.setStatus(staff.getStatus());
        dto.setTypeStaff(staff.getTypeStaff());
        dto.setIdUser(staff.getUser().getId_user());
        return dto;
    }

    public List<StaffDto> convertListStaffDto(List<Staff> staffList) {
        return staffList.stream()
                        .map(this::convertStaffDto)
                        .collect(Collectors.toList());
    }
}

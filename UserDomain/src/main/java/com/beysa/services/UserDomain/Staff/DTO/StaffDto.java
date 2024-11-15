package com.beysa.services.UserDomain.Staff.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private Long idStaff;
    private String name;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private String gender;
    private Long idIdentityDocument; 
    private String documentNumber;
    private String mobileNumber;
    private String email;
    private String address;
    private String image;
    private LocalDateTime dateEntry;
    private Long idGeographicalLocation;
    private BigDecimal salary;
    private String contractType;
    private Integer status;
    private String typeStaff;
}

package com.beysa.services.UserDomain.Staff.DTO;

import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffWithUbication{
    StaffDto staff;
    GeographicalLocationDto ubication;
}

package com.beysa.services.UserDomain.Staff.DTO;

import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;
import com.beysa.services.UserDomain.Medic.DTO.MedicDto;
import com.beysa.services.UserDomain.User.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffMedic {
    StaffDto staff;
    MedicDto medic;
    GeographicalLocationDto geographicalLocation;
    UserEntity user;
    Long idClinic;
}

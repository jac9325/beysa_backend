package com.beysa.services.UserDomain.UserClinic;

import com.beysa.services.UserDomain.UserClinic.DTO.UserClinicDto;

import java.util.List;

public interface UserClinicService {
    Boolean saveUserClinic(UserClinic userClinic);
    UserClinicDto getUserClinicById(Long idUserClinic);
    List<UserClinicDto> getUserClinicByIdClinic(Long idClinic);
}

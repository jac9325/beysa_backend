package com.beysa.services.UserDomain.UserClinic.DTO;

import lombok.Data;

@Data
public class UserClinicDto {
    Long idUserClinic;
    Long idUser;
    Long idClinic;
    Integer status;
}

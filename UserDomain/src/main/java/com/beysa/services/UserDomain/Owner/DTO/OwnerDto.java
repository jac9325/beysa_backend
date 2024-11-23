package com.beysa.services.UserDomain.Owner.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    private Long idOwner;
    private String firstname;
    private String lastname;
    private Long idIdentityDocument;
    private String documentNumber;
    private String email;
    private String phoneNumber;
    private Long idUser;
    private String image;
    private Integer status;
}

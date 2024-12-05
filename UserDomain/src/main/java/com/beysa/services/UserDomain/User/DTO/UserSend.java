package com.beysa.services.UserDomain.User.DTO;

import com.beysa.services.UserDomain.User.UserEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSend {
    private Long idUser;
    private String username;
    private Integer status;
    private Integer isMain;
    private UserEnum typeUser;
    private String email;
}

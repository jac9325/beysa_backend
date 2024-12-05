package com.beysa.services.UserDomain.User;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.User.DTO.UserSend;

@Component
public class UserUtils {
    public UserSend convertUserSendDto(UserEntity user) {
        UserSend userSend = new UserSend();
        userSend.setIdUser(user.getIdUser());
        userSend.setUsername(user.getUsername());
        userSend.setStatus(user.getStatus());
        userSend.setIsMain(user.getIsMain());
        userSend.setTypeUser(user.getTypeUser());
        userSend.setEmail(user.getEmail());
        return userSend;
    }

}

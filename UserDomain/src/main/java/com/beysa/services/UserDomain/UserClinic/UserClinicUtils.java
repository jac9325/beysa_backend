package com.beysa.services.UserDomain.UserClinic;

import com.beysa.services.UserDomain.UserClinic.DTO.UserClinicDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserClinicUtils {
    public UserClinicDto convertUserClinicDto(UserClinic entity) {
        if (entity == null) return null;
        UserClinicDto dto = new UserClinicDto();
        dto.setIdUserClinic(entity.getIdUserClinic());
        dto.setIdUser(entity.getUser().getIdUser());
        dto.setIdClinic(entity.getClinic().getIdClinic());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<UserClinicDto> convertListUserClinicDto(List<UserClinic> entities) {
        return entities.stream()
                .map(this::convertUserClinicDto)
                .collect(Collectors.toList());
    }
}

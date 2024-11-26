package com.beysa.services.UserDomain.Owner;

import com.beysa.services.UserDomain.Owner.DTO.OwnerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OwnerUtils {
    public OwnerDto convertOwnerDto(Owner owner){
        OwnerDto response = new OwnerDto();
        response.setIdOwner(owner.getIdOwner());
        response.setFirstname(owner.getFirstname());
        response.setLastname(owner.getLastname());
        response.setIdIdentityDocument(owner.getIdentityDocument().getIdIdentityDocument());
        response.setDocumentNumber(owner.getDocumentNumber());
        response.setEmail(owner.getEmail());
        response.setPhoneNumber(owner.getPhoneNumber());
        response.setIdUser(owner.getUser().getIdUser());
        response.setImage(owner.getImage());
        response.setStatus(owner.getStatus());
        return response;
    }

    public List<OwnerDto> convertListOwnerDto(List<Owner> listOwner){
        return listOwner.stream()
                .map(this::convertOwnerDto)
                .collect(Collectors.toList());
    }
}

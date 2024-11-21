package com.beysa.services.UserDomain.Owner;

import com.beysa.services.UserDomain.Owner.DTO.OwnerDto;

public interface OwnerService {
    OwnerDto getOwnerById(Long idOwner);
    OwnerDto getOwnerByIdUser(Long idUser);
}

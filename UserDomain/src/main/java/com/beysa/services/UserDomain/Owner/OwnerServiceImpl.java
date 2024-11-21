package com.beysa.services.UserDomain.Owner;

import com.beysa.services.UserDomain.Owner.DTO.OwnerDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository ownerRepository;
    private final OwnerUtils ownerUtils;

    @Transactional(readOnly = true)
    @Override
    public OwnerDto getOwnerById(Long idOwner){
        return ownerRepository.findById(idOwner)
                .map(ownerUtils::convertOwnerDto)
                .orElseThrow(() -> new RuntimeException("Owner not found for id: " + idOwner));
    }

    @Transactional(readOnly = true)
    @Override
    public OwnerDto getOwnerByIdUser(Long idUser){
        return ownerRepository.findByIdUser(idUser)
                .map(ownerUtils::convertOwnerDto)
                .orElseThrow(() -> new RuntimeException("Owner not found for idUser: " + idUser));
    }
}

package com.beysa.services.UserDomain.UserClinic;

import com.beysa.services.UserDomain.UserClinic.DTO.UserClinicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClinicServiceImpl implements UserClinicService {
    @Autowired
    private final UserClinicRepository userClinicRepository;
    private final UserClinicUtils userClinicUtils;

    @Transactional
    @Override
    public Boolean saveUserClinic(UserClinic userClinic){
        try {
            userClinic = userClinicRepository.save(userClinic);
            if (userClinic.getIdUserClinic()<= 0){
                throw new RuntimeException("Error al guardar");
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserClinicDto getUserClinicById(Long idUserClinic){
        return userClinicRepository.findById(idUserClinic)
                .map(userClinicUtils::convertUserClinicDto)
                .orElseThrow(() -> new RuntimeException("Usuario-Clínica no encontrado por el id: " + idUserClinic));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserClinicDto> getUserClinicByIdClinic(Long idClinic){
        List<UserClinic> userClinic = userClinicRepository.findByIdClinic(idClinic);
        if (userClinic.isEmpty()) {
            throw new RuntimeException("No se encontraron registros de Usuario-Clínica en la base de datos.");
        }
        return userClinicUtils.convertListUserClinicDto(userClinic);
    }
}

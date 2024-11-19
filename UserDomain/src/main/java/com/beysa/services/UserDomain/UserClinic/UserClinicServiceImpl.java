package com.beysa.services.UserDomain.UserClinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserClinicServiceImpl implements UserClinicService {
    @Autowired
    UserClinicRepository userClinicRepository;

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
}

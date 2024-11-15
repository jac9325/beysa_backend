package com.beysa.services.UserDomain.Speciality;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SpecialityServiceImpl implements SpecialityService{
    
    private final SpecialityRepository specialityRepository;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository){
        this.specialityRepository = specialityRepository;
    }

    @Transactional(readOnly =  true)
    public List<Speciality> getAllSpeciality(){
        try {
            List<Speciality> currentList = specialityRepository.findAll();
            return currentList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Transactional
    public List<Speciality> addSpecialities(List<Speciality> specialities){
        try {
            List<Speciality> savedSpecialities = specialityRepository.saveAll(specialities);
            return savedSpecialities;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

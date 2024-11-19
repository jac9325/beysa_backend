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
    @Override
    public List<Speciality> getAllSpeciality(){
        try {
            List<Speciality> currentList = specialityRepository.findAll();
            return currentList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Transactional
    @Override
    public List<Speciality> addSpecialities(List<Speciality> specialities){
        try {
            List<Speciality> savedSpecialities = specialityRepository.saveAll(specialities);
            return savedSpecialities;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Transactional
    @Override
    public Speciality getSpeciality(Long idSpeciality){
        try {
            Speciality currenSpeciality = specialityRepository.findById(idSpeciality).orElse(null);
            if (currenSpeciality == null){
                throw new RuntimeException("Ha ocurrido un error al obtener la Especialidad");
            }
            return currenSpeciality;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

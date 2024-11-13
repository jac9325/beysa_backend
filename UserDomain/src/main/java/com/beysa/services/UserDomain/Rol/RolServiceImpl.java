package com.beysa.services.UserDomain.Rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService{

    @Autowired
    private RolRepository rolRepository;

    public Rol getRolById(long id) {
        try {
            Rol currentRol = rolRepository.findById(id).orElse(null);
                if (currentRol == null){
                    return null;
                }else{ return currentRol; }          
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Override
    public List<Rol> getAllRol() {
        try {
            List<Rol> currentListRol = rolRepository.findAll();
            if (currentListRol.isEmpty()){              
                return null;            
            }
            return currentListRol;
        } catch (Exception e) {           
            throw new RuntimeException(e.getMessage());            
        }
    }
}

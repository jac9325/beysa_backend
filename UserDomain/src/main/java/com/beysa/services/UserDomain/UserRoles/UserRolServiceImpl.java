package com.beysa.services.UserDomain.UserRoles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class UserRolServiceImpl implements UserRolService{

    @Autowired
    private UserRolRepository usuarioRolRepository;

    @Override
    public UserRol save(UserRol usuarioRol){
        try {
            UserRol currentUserRol = usuarioRolRepository.save(usuarioRol);
            return currentUserRol;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<UserRol> getAllRol() {
        try {
            List<UserRol> currentListRol = usuarioRolRepository.findAll();
            if (currentListRol.isEmpty()){              
                return null;            
            }else{
                return currentListRol;
            }
        } catch (Exception e) {    
            throw new RuntimeException(e.getMessage());        
        }
    }  

}

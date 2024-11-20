package com.beysa.services.UserDomain.UserPermissions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author HP
 */
@Service
public class UserPermissionsServiceImpl implements UserPermissionsService{

    @Autowired
    private UserPermissionsRepository userPermissionsRepository;

    public UserPermissionsServiceImpl(){
    }

    @Override
    public UserPermissions save(UserPermissions userPermissions){
        try {
            UserPermissions currentUserPermissions = userPermissionsRepository.save(userPermissions);
            return currentUserPermissions;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Boolean saveAllPermissions(List<UserPermissions> list){
        Boolean res = false;
        try {
          for (UserPermissions userPermissions : list) {
            userPermissionsRepository.save(userPermissions);
            res = true;
          }
          return res;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<UserPermissions> getAllPermissions() {
        try {
            List<UserPermissions> currentListPermissions = userPermissionsRepository.findAll();
            if (currentListPermissions.isEmpty()){              
                return null;            
            }else{
                return currentListPermissions;
            }
        } catch (Exception e) {    
            throw new RuntimeException(e.getMessage());        
        }
    }  

    @Transactional(readOnly = true)
    @Override
    public UserPermissions getUserPermissionsById(Long idUserPermissions){
        try {
            UserPermissions currentUserPermissions = userPermissionsRepository.findById(idUserPermissions).orElse(null);
            if (currentUserPermissions == null){
                throw new RuntimeException("Ha ocurrido un error");
            }
            return currentUserPermissions;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());  
        }
    }
}

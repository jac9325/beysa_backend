package com.beysa.services.UserDomain.UserPermissions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class UserPermissionsServiceImpl implements UserPermissionsService{

    @Autowired
    private UserPermissionsRepository userPermissionsRepository;

    @Override
    public UserPermissions save(UserPermissions userPermissions){
        try {
            UserPermissions currentUserPermissions = userPermissionsRepository.save(userPermissions);
            return currentUserPermissions;
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

}

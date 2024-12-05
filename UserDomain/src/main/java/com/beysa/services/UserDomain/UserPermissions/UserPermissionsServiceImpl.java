package com.beysa.services.UserDomain.UserPermissions;

import java.security.Permission;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.Permissions.PermissionsEntity;
import com.beysa.services.UserDomain.Permissions.PermissionsService;
import com.beysa.services.UserDomain.User.UserEntity;
import com.beysa.services.UserDomain.User.UserService;
import com.beysa.services.UserDomain.UserPermissions.DTO.UserPermissionsDto;


/**
 *
 * @author HP
 */
@Service
public class UserPermissionsServiceImpl implements UserPermissionsService{

    @Autowired
    private UserPermissionsRepository userPermissionsRepository;

    private UserService userService;
    private PermissionsService permissionsService;
    private UserPermissionsUtils userPermissionsUtils;
    public UserPermissionsServiceImpl(
        UserService userService,
        PermissionsService permissionsService,
        UserPermissionsUtils userPermissionsUtils
    ){
        this.userService = userService;
        this.permissionsService = permissionsService;
        this.userPermissionsUtils = userPermissionsUtils;
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

    @Transactional
    @Override
    public Boolean deteleUserPermisisions(List<UserPermissionsDto> userPermissionsDtos){
        try {
            Boolean response = false;
            if (userPermissionsDtos == null){
                throw new RuntimeException("No hay permisos que eliminar");
            }
            for (UserPermissionsDto userPermissionsDto : userPermissionsDtos) { 
                UserPermissions currentPermissions = userPermissionsRepository.findById(userPermissionsDto.getIdUserPermissions()).orElse(null);
                if (currentPermissions == null){
                    throw new RuntimeException("Ha ocurrido un error al obtener el Permiso");
                }
                userPermissionsRepository.delete(currentPermissions);
                response = true;
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Boolean addUserPermisisions(List<UserPermissionsDto> userPermissionsDtos){
        try {
            Boolean response = false;
            if (userPermissionsDtos == null){
                throw new RuntimeException("No hay permisos que eliminar");
            }
            List<UserPermissions> currentListPermissions = getListUserPermissionsByUser(userPermissionsDtos.get(0).getIdUser());
            if (currentListPermissions.size() <= 0){
                for (UserPermissionsDto userPermissionsDto : userPermissionsDtos) { 
                    UserEntity currentUser = userService.getUserById(userPermissionsDto.getIdUser());
                    if (currentUser == null){
                        throw new RuntimeException("No existe el usuario");
                    }
                    PermissionsEntity currentPermissions = permissionsService.getPermissionById(userPermissionsDto.getIdPermissions());
                    if (currentPermissions == null){
                        throw new RuntimeException("No existe el permiso");
                    }
                    UserPermissions newUserPermissions = new UserPermissions();
                    newUserPermissions.setIdUserPermissions(userPermissionsDto.getIdUserPermissions());
                    newUserPermissions.setPermissions(currentPermissions);
                    newUserPermissions.setUser(currentUser);
                    newUserPermissions.setStatus(1);
                    newUserPermissions = userPermissionsRepository.save(newUserPermissions);
                    if (newUserPermissions.getIdUserPermissions() <= 0){
                        throw new RuntimeException("Ha ocurrido un error al guardar el permiso");
                    }              
                    response = true;
                }
            }else{
                for (UserPermissionsDto userPermissionsDto : userPermissionsDtos) { 
                    UserEntity currentUser = userService.getUserById(userPermissionsDto.getIdUser());
                    if (currentUser == null){
                        throw new RuntimeException("No existe el usuario");
                    }
                    PermissionsEntity currentPermissions = permissionsService.getPermissionById(userPermissionsDto.getIdPermissions());
                    if (currentPermissions == null){
                        throw new RuntimeException("No existe el permiso");
                    }
                    Boolean exists = currentListPermissions.stream()
                        .anyMatch(up -> up.getPermissions().equals(currentPermissions) && up.getStatus() == 1);
                    if (exists == false){
                        UserPermissions newUserPermissions = new UserPermissions();
                    newUserPermissions.setIdUserPermissions(0L);
                    newUserPermissions.setPermissions(currentPermissions);
                    newUserPermissions.setUser(currentUser);
                    newUserPermissions.setStatus(1);
                    userPermissionsRepository.save(newUserPermissions);
                    if (newUserPermissions.getIdUserPermissions() <= 0){
                        throw new RuntimeException("Ha ocurrido un error al guardar el permiso");
                    }              
                    response = true;
                    }                
                }
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<UserPermissions> getListUserPermissionsByUser(Long idUser){
        try {
            List<UserPermissions> list = userPermissionsRepository.getListUserPermissionsByUser(idUser);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserPermissionsDto> getListUserPermissionsByUserDto(Long idUser){
        try {
            List<UserPermissions> list = userPermissionsRepository.getListUserPermissionsByUser(idUser);
            List<UserPermissionsDto> listResponse = userPermissionsUtils.convertListPermissionsDto(list);
            return listResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

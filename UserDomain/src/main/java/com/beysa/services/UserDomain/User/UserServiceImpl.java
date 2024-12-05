package com.beysa.services.UserDomain.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.Clinic.Clinic;
import com.beysa.services.UserDomain.Configuration.UsuarioSecurityConfig;
import com.beysa.services.UserDomain.Rol.Rol;
import com.beysa.services.UserDomain.User.DTO.UserSend;
import com.beysa.services.UserDomain.UserClinic.UserClinic;
import com.beysa.services.UserDomain.UserClinic.UserClinicService;
import com.beysa.services.UserDomain.UserRoles.UserRol;
import com.beysa.services.UserDomain.UserRoles.UserRolService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    private final UserClinicService userClinicService;
    private final UserRolService userRolService;
    private final UserUtils userUtils;

    public UserServiceImpl(
        UserClinicService userClinicService,
        UserRolService userRolService,
        UserUtils userUtils
    ){
        this.userClinicService = userClinicService;
        this.userRolService = userRolService;
        this.userUtils = userUtils;
    }

    @Transactional
    public UserEntity createUsuario(UserEntity request) {
        try {
            String passwordEncode = UsuarioSecurityConfig.encode(request.getPassword());
            request.setPassword(passwordEncode);
            UserEntity objectResponse = userRepository.save(request);
            return objectResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public UserEntity createUserAll(UserEntity currentUser, List<Rol> currentRols, Clinic clinic){
        try {
            if (currentUser == null) {
                return null;
            }
            if (currentRols == null || currentRols.size() <= 0) {
                throw new RuntimeException("Ha ocurrido un error al obtener los roles");
            }
            currentUser = createUsuario(currentUser);
            if (currentUser.getIdUser()<=0) {
                return null;
            }
            for (Rol rol : currentRols) {               
                UserRol currentUsuarioRol = new UserRol();
                currentUsuarioRol.setRol(rol);
                currentUsuarioRol.setUser(currentUser);
                currentUsuarioRol = userRolService.save(currentUsuarioRol);
                if (currentUsuarioRol.getId_rol_user() <= 0) {
                    return null;
                }
            }     
            UserClinic currentUserClinic = new UserClinic();
            currentUserClinic.setClinic(clinic);
            currentUserClinic.setIdUserClinic(0L);
            currentUserClinic.setStatus(1);
            currentUserClinic.setUser(currentUser);
            Boolean resp = userClinicService.saveUserClinic(currentUserClinic);  
            if (resp==false){
                throw new RuntimeException("Error al guardar la relación entre Usuario y Clínica");
            }
            return currentUser;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
   }

   @Transactional
   @Override
   public Boolean changePasswordStaff(UserEntity newUser){
    try {
        Boolean res = false;
        if (newUser == null){
            throw new RuntimeException("El usuario enviado esta vacío");
        }
        if (newUser.getTypeUser() == UserEnum.TYPE_STAFF){
            UserEntity oldUserEntity = userRepository.findById(newUser.getIdUser()).orElse(newUser);
            if (oldUserEntity == null){
                throw new RuntimeException("Error al obtener el Usuario");
            }
            String passwordEncode = UsuarioSecurityConfig.encode(newUser.getPassword());
            oldUserEntity.setPassword(passwordEncode);
            userRepository.save(oldUserEntity);
            res = true;
        }
        return res;
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }
   }

   @Transactional(readOnly = true)
   @Override
   public UserEntity getUserById(Long idUser){
    try {
        UserEntity currentUser = userRepository.findById(idUser).orElse(null);
        if (currentUser==null){
            throw new RuntimeException("Ha ocurrido un error al obtener el Usuario");
        }
        return currentUser;
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }
   }

   @Transactional
   @Override
   public UserSend findByUsernameUserSend(String username){
    try {
        UserEntity currentUser = userRepository.findByUserName(username).orElse(null);
        UserSend userResponse = userUtils.convertUserSendDto(currentUser);
        return userResponse;
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }
   }
}

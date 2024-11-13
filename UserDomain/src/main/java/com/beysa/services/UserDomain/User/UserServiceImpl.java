package com.beysa.services.UserDomain.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.Configuration.UsuarioSecurityConfig;
import com.beysa.services.UserDomain.Rol.Rol;
import com.beysa.services.UserDomain.UserRoles.UserRol;
import com.beysa.services.UserDomain.UserRoles.UserRolService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserRolService userRolService;

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
    public Boolean createUserAll(UserEntity currentUser, List<Rol> currentRols){
        try {
            if (currentUser == null) {
                return null;
            }
            if (currentRols == null || currentRols.size() <= 0) {
                throw new RuntimeException("Ha ocurrido un error al obtener los roles");
            }
            currentUser = createUsuario(currentUser);
            if (currentUser.getId_user()<=0) {
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
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
   }

}

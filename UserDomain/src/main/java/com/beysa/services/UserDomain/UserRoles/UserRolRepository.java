package com.beysa.services.UserDomain.UserRoles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author HP
 */
public interface UserRolRepository extends JpaRepository<UserRol, Long> {
    @Query(value="SELECT * FROM t_rol_user WHERE id_user = ?1", nativeQuery = true)
    List<UserRol> getUserRolList(Long idUser);
}


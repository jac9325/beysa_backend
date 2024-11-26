package com.beysa.services.UserDomain.UserPermissions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author HP
 */
public interface UserPermissionsRepository extends JpaRepository<UserPermissions, Long> {
    @Query(value ="SELECT * FROM t_user_permissions where id_user = ?1", nativeQuery = true)
    List<UserPermissions> getListUserPermissionsByUser(Long idUser);
}

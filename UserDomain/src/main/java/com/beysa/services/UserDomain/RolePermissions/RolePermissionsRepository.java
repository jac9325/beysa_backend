package com.beysa.services.UserDomain.RolePermissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolePermissionsRepository extends JpaRepository<RolePermissions, Long> {
    @Query(value="SELECT * FROM t_role_permissions WHERE id_rol = ?1", nativeQuery = true)
    List<RolePermissions> findByIdRole(Long idRole);

}

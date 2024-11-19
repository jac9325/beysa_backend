package com.beysa.services.UserDomain.Rol;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author HP
 */
public interface RolRepository extends JpaRepository<Rol, Long> {
    @Query(value="SELECT * FROM t_rol WHERE name = ?1", nativeQuery = true)
    Optional<Rol> getRolByName(String name);

    @Query(value = "SELECT r.* FROM t_rol as r INNER JOIN t_usuario_roles as ur ON r.id_rol = ur.id_rol where ur.id_usuario = ?1", nativeQuery = true)
    List<Rol> getListRolByUser(Long idUser);
}


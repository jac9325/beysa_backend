package com.beysa.services.UserDomain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Jaime Amilcar Catari Sinsaya
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @Query(value ="SELECT * FROM t_user where username = ?1 and status = 1", nativeQuery = true)
    Optional<UserEntity> findByUserName(String username);
}

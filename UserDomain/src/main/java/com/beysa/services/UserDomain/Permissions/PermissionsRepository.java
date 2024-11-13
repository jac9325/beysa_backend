package com.beysa.services.UserDomain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author 
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @Query(value ="SELECT * FROM t_permissions where status = 1", nativeQuery = true)
}


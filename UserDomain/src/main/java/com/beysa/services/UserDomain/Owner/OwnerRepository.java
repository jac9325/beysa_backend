package com.beysa.services.UserDomain.Owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query(value="SELECT * FROM t_owner WHERE id_user = ?1", nativeQuery = true)
    Optional<Owner> findByIdUser(Long idUser);
}

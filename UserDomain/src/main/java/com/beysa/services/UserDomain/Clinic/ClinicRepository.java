package com.beysa.services.UserDomain.Clinic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    @Query(value ="select c.* from t_user_clinic as uc JOIN t_clinic as c on uc.id_clinic = c.id_clinic WHERE uc.id_user = ?1", nativeQuery = true)
    Optional<Clinic> findClinicByUser(Long idUser);
}

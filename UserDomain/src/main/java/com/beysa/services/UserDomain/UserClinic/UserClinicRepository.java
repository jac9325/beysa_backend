package com.beysa.services.UserDomain.UserClinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserClinicRepository extends JpaRepository<UserClinic, Long> {
    @Query(value="SELECT * FROM t_user_clinic WHERE id_clinic = ?1", nativeQuery = true)
    List<UserClinic> findByIdClinic(Long idClinic);
}

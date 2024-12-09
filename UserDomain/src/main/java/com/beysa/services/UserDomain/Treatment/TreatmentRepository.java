package com.beysa.services.UserDomain.Treatment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    @Query(value="SELECT * FROM t_treatment WHERE id_clinic = ?1", nativeQuery = true)
    List<Treatment> findByIdClinic(Long idClinic);
}

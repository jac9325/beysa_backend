package com.beysa.services.UserDomain.PatientClinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientClinicRepository extends JpaRepository<PatientClinic, Long> {
    @Query(value="SELECT * FROM t_patient_clinic WHERE id_clinic = ?1", nativeQuery = true)
    List<PatientClinic> findByIdClinic(Long idClinic);
}

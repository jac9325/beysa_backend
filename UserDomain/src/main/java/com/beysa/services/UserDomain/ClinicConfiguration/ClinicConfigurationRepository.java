package com.beysa.services.UserDomain.ClinicConfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClinicConfigurationRepository extends JpaRepository<ClinicConfiguration, Long> {
    @Query(value="SELECT * FROM t_clinic_configuration WHERE id_clinic = ?1", nativeQuery = true)
    Optional<ClinicConfiguration> findByIdClinic(Long idClinic);
}

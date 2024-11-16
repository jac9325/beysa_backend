package com.beysa.services.UserDomain.ClinicConfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClinicConfigurationRepository extends JpaRepository<ClinicConfiguration, Long> {
    Optional<ClinicConfiguration> findByIdClinic(Long idClinic);
}

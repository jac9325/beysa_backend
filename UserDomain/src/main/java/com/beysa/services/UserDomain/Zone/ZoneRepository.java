package com.beysa.services.UserDomain.Zone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    @Query(value="SELECT * FROM t_zone WHERE id_treatment = ?1", nativeQuery = true)
    List<Zone> findByIdTreatment(Long idTreatment);
}

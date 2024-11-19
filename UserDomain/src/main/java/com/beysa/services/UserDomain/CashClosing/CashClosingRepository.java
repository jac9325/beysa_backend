package com.beysa.services.UserDomain.CashClosing;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CashClosingRepository extends JpaRepository<CashClosing, Long> {
    //Optional<CashClosing> findByIdCashSession(Long idCashSession);
}

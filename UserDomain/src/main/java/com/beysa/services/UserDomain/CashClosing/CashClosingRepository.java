package com.beysa.services.UserDomain.CashClosing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CashClosingRepository extends JpaRepository<CashClosing, Long> {
    @Query(value="SELECT * FROM t_cash_closing WHERE id_cash_session = ?1", nativeQuery = true)
    Optional<CashClosing> findByIdCashSession(Long idCashSession);
}

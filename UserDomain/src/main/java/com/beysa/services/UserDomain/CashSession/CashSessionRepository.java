package com.beysa.services.UserDomain.CashSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CashSessionRepository extends JpaRepository<CashSession, Long> {
    @Query(value="SELECT * FROM t_cash_session WHERE id_cash_register = ?1", nativeQuery = true)
    List<CashSession> findByIdCashRegister(Long idCashRegister);
}

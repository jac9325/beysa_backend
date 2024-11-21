package com.beysa.services.UserDomain.Income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    @Query(value="SELECT * FROM t_income WHERE id_cash_session = ?1", nativeQuery = true)
    List<Income> findByIdCashSession(Long idCashSession);
}

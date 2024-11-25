package com.beysa.services.UserDomain.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query(value="SELECT * FROM t_expense WHERE id_cash_session = ?1", nativeQuery = true)
    List<Expense> findByIdCashSession(Long idCashSession);
}

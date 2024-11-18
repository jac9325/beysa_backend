package com.beysa.services.UserDomain.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByIdCashSession(Long idCashSession);
}

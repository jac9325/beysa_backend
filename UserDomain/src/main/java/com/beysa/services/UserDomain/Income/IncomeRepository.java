package com.beysa.services.UserDomain.Income;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByIdCashSession(Long idCashSession);
}

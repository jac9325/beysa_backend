package com.beysa.services.UserDomain.Income;

import com.beysa.services.UserDomain.Income.DTO.IncomeDto;

import java.util.List;

public interface IncomeService {
    IncomeDto getIncomeById(Long idIncome);
    List<IncomeDto> getIncomeByIdCashSession(Long idCashSession);
}

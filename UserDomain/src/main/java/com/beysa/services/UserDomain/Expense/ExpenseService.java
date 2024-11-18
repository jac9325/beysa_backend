package com.beysa.services.UserDomain.Expense;

import com.beysa.services.UserDomain.Expense.DTO.ExpenseDto;

import java.util.List;

public interface ExpenseService {
    ExpenseDto getExpenseById(Long idExpense);
    List<ExpenseDto> getExpenseByIdCashSession(Long idCashSession);
}

package com.beysa.services.UserDomain.Expense;

import com.beysa.services.UserDomain.Expense.DTO.ExpenseDto;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
@Component
public class ExpenseUtils {
    public ExpenseDto convertExpenseDto(Expense expense){
        ExpenseDto response = new ExpenseDto();
        response.setIdExpense(expense.getIdExpense());
        response.setAmount(expense.getAmount());
        response.setReason(expense.getReason());
        response.setTypeExpense(expense.getTypeExpense());
        response.setDateExpense(expense.getDateExpense());
        response.setNotes(expense.getNotes());
        response.setIsManually(expense.getIsManually());
        response.setIdCashSession(expense.getCashSession().getIdCashSession());
        response.setStatus(expense.getStatus());
        return response;
    }

    public List<ExpenseDto> convertListExpenseDto(List<Expense> listExpense){
        return listExpense.stream()
                .map(this::convertExpenseDto)
                .collect(Collectors.toList());
    }
}

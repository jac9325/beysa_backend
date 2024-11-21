package com.beysa.services.UserDomain.Expense;

import com.beysa.services.UserDomain.Expense.DTO.ExpenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseUtils expenseUtils;

    @Transactional(readOnly = true)
    @Override
    public ExpenseDto getExpenseById(Long idExpense){
        return expenseRepository.findById(idExpense)
                .map(expenseUtils::convertExpenseDto)
                .orElseThrow(() -> new RuntimeException("Expense not found for id: " + idExpense));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExpenseDto> getExpenseByIdCashSession(Long idCashSession){
        List<Expense> expenses = expenseRepository.findByIdCashSession(idCashSession);
        if (expenses.isEmpty()) {
            throw new RuntimeException("Expense not found for idCashSession: " + idCashSession);
        }
        return expenseUtils.convertListExpenseDto(expenses);
    }
}

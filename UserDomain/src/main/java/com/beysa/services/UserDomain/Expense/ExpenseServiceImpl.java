package com.beysa.services.UserDomain.Expense;

import com.beysa.services.UserDomain.Expense.DTO.ExpenseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseUtils expenseUtils;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseUtils expenseUtils){
        this.expenseRepository = expenseRepository;
        this.expenseUtils = expenseUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public ExpenseDto getExpenseById(Long idExpense){
        return expenseRepository.findById(idExpense)
                .map(expenseUtils::convertExpenseDto)
                .orElseThrow(() -> new RuntimeException("Egreso no encontrado por el id: " + idExpense));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExpenseDto> getExpenseByIdCashSession(Long idCashSession){
        List<Expense> expenses = expenseRepository.findByIdCashSession(idCashSession);
        if (expenses.isEmpty()) {
            throw new RuntimeException("Egreso no encontrado por el id de CajaSesión: " + idCashSession);
        }
        return expenseUtils.convertListExpenseDto(expenses);
    }
}

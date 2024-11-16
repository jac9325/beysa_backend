package com.beysa.services.UserDomain.Expense.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseDto {
    private Long idExpense;
    private BigDecimal amount;
    private String reason;
    private String typeExpense;
    private LocalDateTime dateExpense;
    private String notes;
    private Long IdCashSession;
    private Integer isManually;
    private Integer status;
}

package com.beysa.services.UserDomain.Income.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class IncomeDto {
    private Long idIncome;
    private BigDecimal amount;
    private String reason;
    private String typeIncome;
    private LocalDateTime dateIncome;
    private String notes;
    private Integer isManually;
    private Long idCashSession;
    private Integer status;
}

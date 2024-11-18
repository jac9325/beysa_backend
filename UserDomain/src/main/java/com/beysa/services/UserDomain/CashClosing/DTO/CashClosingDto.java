package com.beysa.services.UserDomain.CashClosing.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CashClosingDto {
    private Long idCashClosing;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String notes;
    private BigDecimal initialAmount;
    private BigDecimal closingAmount;
    private String closingTime;
    private BigDecimal shortage;
    private BigDecimal surplus;
    private BigDecimal physicalTotal;
    private BigDecimal realAmount;
    private Integer forceSave;
    private Long IdCashSession;
    private Integer status;
}

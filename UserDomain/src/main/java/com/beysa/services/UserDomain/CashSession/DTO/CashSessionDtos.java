package com.beysa.services.UserDomain.CashSession.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashSessionDtos {
    private Long idCollaborator;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal initialAmount;
    private Long idcashRegister; 
    private Integer status;
}

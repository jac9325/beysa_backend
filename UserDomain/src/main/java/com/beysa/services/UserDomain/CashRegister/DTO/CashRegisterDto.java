package com.beysa.services.UserDomain.CashRegister.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashRegisterDto {
    private Long idCashRegister;
    private String name;
    private Integer status;
    private LocalDateTime createAd;
    private LocalDateTime updateAd;
    private Long idCollaborator;
}

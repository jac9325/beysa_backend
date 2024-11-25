package com.beysa.services.UserDomain.CashRegister;

import com.beysa.services.UserDomain.CashRegister.DTO.CashRegisterDto;

public interface CashRegisterService {
    CashRegisterDto getCashRegisterById(Long idCashRegister);
}

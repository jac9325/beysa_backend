package com.beysa.services.UserDomain.CashClosing;

import com.beysa.services.UserDomain.CashClosing.DTO.CashClosingDto;

public interface CashClosingService {
    CashClosingDto getCashClosingById(Long idCashClosing);
    //CashClosingDto getCashClosingByIdCashSession(Long idCashSession);
}

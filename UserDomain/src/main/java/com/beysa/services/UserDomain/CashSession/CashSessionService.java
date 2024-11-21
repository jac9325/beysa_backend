package com.beysa.services.UserDomain.CashSession;

import com.beysa.services.UserDomain.CashSession.DTO.CashSessionDto;

import java.util.List;

public interface CashSessionService {
    CashSessionDto getCashSessionById(Long idCashSession);
    List<CashSessionDto> getCashSessionByIdCashRegister(Long idCashRegister);
}

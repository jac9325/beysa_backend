package com.beysa.services.UserDomain.CashSession;

import com.beysa.services.UserDomain.CashSession.DTO.CashSessionDtos;

import java.util.List;

public interface CashSessionService {
    CashSessionDtos getCashSessionById(Long idCashSession);
    List<CashSessionDtos> getCashSessionByIdCashRegister(Long idCashRegister);
}

package com.beysa.services.UserDomain.CashSession;

import com.beysa.services.UserDomain.CashSession.DTO.CashSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CashSessionServiceImpl implements CashSessionService{
    private final CashSessionRepository cashSessionRepository;
    private final CashSessionUtils cashSessionUtils;

    @Transactional(readOnly = true)
    @Override
    public CashSessionDto getCashSessionById(Long idCashSession){
        return cashSessionRepository.findById(idCashSession)
                .map(cashSessionUtils::convertCashSessionDto)
                .orElseThrow(() -> new RuntimeException("CashSession not found for id: " + idCashSession));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CashSessionDto> getCashSessionByIdCashRegister(Long idCashRegister){
        List<CashSession> cashSession = cashSessionRepository.findByIdCashRegister(idCashRegister);
        if (cashSession.isEmpty()) {
            throw new RuntimeException("No cashSession records found in the database.");
        }
        return cashSessionUtils.convertListCashSessionDto(cashSession);
    }
}

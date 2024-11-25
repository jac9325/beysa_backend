package com.beysa.services.UserDomain.CashSession;

import com.beysa.services.UserDomain.CashSession.DTO.CashSessionDtos;
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
    public CashSessionDtos getCashSessionById(Long idCashSession){
        return cashSessionRepository.findById(idCashSession)
                .map(cashSessionUtils::convertCashSessionDtos)
                .orElseThrow(() -> new RuntimeException(" CajaSesión no encontrado por el id: " + idCashSession));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CashSessionDtos> getCashSessionByIdCashRegister(Long idCashRegister){
        List<CashSession> cashSession = cashSessionRepository.findByIdCashRegister(idCashRegister);
        if (cashSession.isEmpty()) {
            throw new RuntimeException(" No se encontraron registros de CajaSesión en la base de datos.");
        }
        return cashSessionUtils.convertListCashSessionDtos(cashSession);
    }
}

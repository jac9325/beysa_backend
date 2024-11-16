package com.beysa.services.UserDomain.CashClosing;

import com.beysa.services.UserDomain.CashClosing.DTO.CashClosingDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashClosingServiceImpl implements CashClosingService{
    
    private final CashClosingRepository cashClosingRepository;
    private final CashClosingUtils cashClosingUtils;

    public CashClosingServiceImpl(CashClosingRepository cashClosingRepository, CashClosingUtils cashClosingUtils){
        this.cashClosingRepository = cashClosingRepository;
        this.cashClosingUtils = cashClosingUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public CashClosingDto getCashClosingById(Long idCashClosing){
        return cashClosingRepository.findById(idCashClosing)
                .map(cashClosingUtils::convertCashClosingDto)
                .orElseThrow(() -> new RuntimeException("CashClosing not found for id: " + idCashClosing));
    }

    @Transactional(readOnly = true)
    @Override
    public CashClosingDto getCashClosingByIdCashSession(Long idCashSession){
        return cashClosingRepository.findByIdCashSession(idCashSession)
                .map(cashClosingUtils::convertCashClosingDto)
                .orElseThrow(() -> new RuntimeException("CashClosing not found for idCashSession: " + idCashSession));
    }
}

package com.beysa.services.UserDomain.CashRegister;

import com.beysa.services.UserDomain.CashRegister.DTO.CashRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashRegisterServiceImpl implements CashRegisterService{

    private final CashRegisterRepository cashRegisterRepository;
    private final CashRegisterUtils cashRegisterUtils;

    @Transactional(readOnly = true)
    @Override
    public CashRegisterDto getCashRegisterById(Long idCashRegister){
        return cashRegisterRepository.findById(idCashRegister)
                .map(cashRegisterUtils::convertCashRegisterDto)
                .orElseThrow(() -> new RuntimeException("CashRegister not found for id: " + idCashRegister));
    }
}

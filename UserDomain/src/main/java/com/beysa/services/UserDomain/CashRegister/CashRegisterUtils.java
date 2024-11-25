package com.beysa.services.UserDomain.CashRegister;

import com.beysa.services.UserDomain.CashRegister.DTO.CashRegisterDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CashRegisterUtils {
    public CashRegisterDto convertCashRegisterDto(CashRegister cashRegister){
        CashRegisterDto response = new CashRegisterDto();
        response.setIdCashRegister(cashRegister.getIdCashRegister());
        response.setName(cashRegister.getName());
        response.setCreateAd(cashRegister.getCreateAd());
        response.setUpdateAd(cashRegister.getUpdateAd());
        response.setIdCollaborator(cashRegister.getCollaborator().getIdCollaborator());
        response.setStatus(cashRegister.getStatus());
        return response;
    }

    public List<CashRegisterDto> convertListCashRegisterDto(List<CashRegister> listCashRegister){
        return listCashRegister.stream()
                .map(this::convertCashRegisterDto)
                .collect(Collectors.toList());
    }
}

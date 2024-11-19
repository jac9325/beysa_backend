package com.beysa.services.UserDomain.CashClosing;

import com.beysa.services.UserDomain.CashClosing.DTO.CashClosingDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CashClosingUtils {
    public CashClosingDto convertCashClosingDto(CashClosing cashClosing){
        CashClosingDto response = new CashClosingDto();
        response.setIdCashClosing(cashClosing.getIdCashClosing());
        response.setStartDate(cashClosing.getStartDate());
        response.setEndDate(cashClosing.getEndDate());
        response.setNotes(cashClosing.getNotes());
        response.setInitialAmount(cashClosing.getInitialAmount());
        response.setClosingAmount(cashClosing.getClosingAmount());
        response.setClosingTime(cashClosing.getClosingTime());
        response.setShortage(cashClosing.getShortage());
        response.setSurplus(cashClosing.getSurplus());
        response.setPhysicalTotal(cashClosing.getPhysicalTotal());
        response.setRealAmount(cashClosing.getRealAmount());
        response.setForceSave(cashClosing.getForceSave());
        response.setIdCashSession(cashClosing.getCashSession().getIdCashSession());
        response.setStatus(cashClosing.getStatus());
        return response;
    }

    public List<CashClosingDto> convertListCashClosingDto(List<CashClosing> listCashClosing){
        return listCashClosing.stream()
                .map(this::convertCashClosingDto)
                .collect(Collectors.toList());
    }
}

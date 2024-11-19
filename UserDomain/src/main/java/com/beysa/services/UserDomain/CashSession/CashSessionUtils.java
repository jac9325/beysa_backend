package com.beysa.services.UserDomain.CashSession;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.CashSession.DTO.CashSessionDtos;

@Component
public class CashSessionUtils {
    public CashSessionDtos convertSessionDtos(CashSession entity) {
        if (entity == null) return null;
        CashSessionDtos dto = new CashSessionDtos();
        dto.setIdCashSession(entity.getIdCashSession());
        dto.setName(entity.getName());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setInitialAmount(entity.getInitialAmount());
        dto.setIdcashRegister(entity.getCashRegister().getIdCashRegister());
        dto.setStatus(entity.getStatus());
        dto.setNotes(entity.getNotes());
        return dto;
    }

    public List<CashSessionDtos> toDTOList(List<CashSession> entities) {
        return entities.stream()
                       .map(this::convertSessionDtos)
                       .collect(Collectors.toList());
    }
}

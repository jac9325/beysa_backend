package com.beysa.services.UserDomain.Income;

import com.beysa.services.UserDomain.Income.DTO.IncomeDto;
import com.beysa.services.UserDomain.Income.Income;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
@Component
public class IncomeUtils {
    public IncomeDto convertIncomeDto(Income income){
        IncomeDto response = new IncomeDto();
        response.setIdIncome(income.getIdIncome());
        response.setAmount(income.getAmount());
        response.setReason(income.getReason());
        response.setTypeIncome(income.getTypeIncome());
        response.setDateIncome(income.getDateIncome());
        response.setNotes(income.getNotes());
        response.setIsManually(income.getIsManually());
        response.setIdCashSession(income.getCashSession().getIdCashSession());
        response.setStatus(income.getStatus());
        return response;
    }

    public List<IncomeDto> convertListIncomeDto(List<Income> listIncome){
        return listIncome.stream()
                .map(this::convertIncomeDto)
                .collect(Collectors.toList());
    }
}

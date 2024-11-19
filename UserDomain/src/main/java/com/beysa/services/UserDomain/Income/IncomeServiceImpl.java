package com.beysa.services.UserDomain.Income;

import com.beysa.services.UserDomain.Income.DTO.IncomeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{
    
    private final IncomeRepository incomeRepository;
    private final IncomeUtils incomeUtils;

    public IncomeServiceImpl(IncomeRepository incomeRepository, IncomeUtils incomeUtils){
        this.incomeRepository = incomeRepository;
        this.incomeUtils = incomeUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public IncomeDto getIncomeById(Long idIncome){
        return incomeRepository.findById(idIncome)
                .map(incomeUtils::convertIncomeDto)
                .orElseThrow(() -> new RuntimeException("Income not found for id: " + idIncome));
    }

    // @Transactional(readOnly = true)
    // @Override
    // public List<IncomeDto> getIncomeByIdCashSession(Long idCashSession){
    //     List<Income> incomes = incomeRepository.findByIdCashSession(idCashSession);
    //     if (incomes.isEmpty()) {
    //         throw new RuntimeException("Income not found for idCashSession: " + idCashSession);
    //     }
    //     return incomeUtils.convertListIncomeDto(incomes);
    // }
}

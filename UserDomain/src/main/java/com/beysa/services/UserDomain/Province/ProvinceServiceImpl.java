package com.beysa.services.UserDomain.Province;

import com.beysa.services.UserDomain.Province.DTO.ProvinceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService{
    
    private final ProvinceRepository provinceRepository;
    private final ProvinceUtils provinceUtils;
    
    public ProvinceServiceImpl(ProvinceRepository provinceRepository, ProvinceUtils provinceUtils){
        this.provinceRepository = provinceRepository;
        this.provinceUtils = provinceUtils;
    }
    
    @Transactional(readOnly = true)
    @Override
    public ProvinceDto getProvinceById(Long idProvince){
        return provinceRepository.findById(idProvince)
                .map(provinceUtils::convertProvinceDto)
                .orElseThrow(() -> new RuntimeException("Province not found for id: " + idProvince));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProvinceDto> getAllProvince(){
        List<Province> listProvince = provinceRepository.findAll();
        if(listProvince.isEmpty()){
            throw new RuntimeException("No Province records found in the database");
        }
        return provinceUtils.convertListProvinceDto(listProvince);
    }
}

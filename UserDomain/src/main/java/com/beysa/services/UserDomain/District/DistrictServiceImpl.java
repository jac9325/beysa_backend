package com.beysa.services.UserDomain.District;

import com.beysa.services.UserDomain.District.DTO.DistrictDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictUtils districtUtils;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictUtils districtUtils){
        this.districtRepository = districtRepository;
        this.districtUtils = districtUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public DistrictDto getDistrictById(Long idDistrict){
        return districtRepository.findById(idDistrict)
                .map(districtUtils::convertDistrictDto)
                .orElseThrow(() -> new RuntimeException("Distrito no encontrado por el id: " + idDistrict));
    }

    @Transactional(readOnly = true)
    @Override
    public District getDistrictByIdEntity(Long idDistrict){
        return districtRepository.findById(idDistrict)
                .orElseThrow(() -> new RuntimeException("Distrito no encontrado por el id: " + idDistrict));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DistrictDto> getAllDistrict(){
        List<District> listDistrict = districtRepository.findAll();
        if(listDistrict.isEmpty()){
            throw new RuntimeException("No se encontraron registros de Distrito en la base de datos.");
        }
        return districtUtils.convertListDistrictDto(listDistrict);
    }
}

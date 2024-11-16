package com.beysa.services.UserDomain.Medic;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicServiceImpl implements MedicService{

    private final MedicRepository medicRepository;
    private final MedicUtils medicUtils;

    public MedicServiceImpl(MedicRepository medicRepository, MedicUtils medicUtils){
        this.medicRepository = medicRepository;
        this.medicUtils = medicUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public MedicDto getMedicById(Long idMedic){
        return medicRepository.findById(idMedic)
                .map(medicUtils::convertMedicDto)
                .orElseThrow(() -> new RuntimeException("Medic not found for id: " + idMedic));
    }

    @Transactional(readOnly = true)
    @Override
    public List<MedicDto> getAllMedic(){
        List<Medic> listMedic = medicRepository.findAll();
        if(listMedic.isEmpty()){
            throw new RuntimeException("No Medic records found in the database");
        }
        return medicUtils.convertListMedicDto(listMedic);
    }

    @Transactional
    @Override
    public MedicDto createMedic(MedicDto request){
        if(request == null) throw new RuntimeException("MedicDto is null");
        Medic medic = medicUtils.convertMedicEntity(request);
        medicRepository.save(medic);
        return medicUtils.convertMedicDto(medic);
    }
}

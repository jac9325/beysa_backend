package com.beysa.services.UserDomain.Treatment;

import com.beysa.services.UserDomain.Treatment.DTO.TreatmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {
    
    private final TreatmentRepository treatmentRepository;
    private final TreatmentUtils treatmentUtils;

    @Transactional(readOnly = true)
    @Override
    public TreatmentDto getTreatmentById(Long idTreatment){
        return treatmentRepository.findById(idTreatment)
                .map(treatmentUtils::convertTreatmentDto)
                .orElseThrow(() -> new RuntimeException("Treatment no encontrado por el id: " + idTreatment));
    }

    @Transactional(readOnly = true)
    @Override
    public List<TreatmentDto> getAllTreatment(){
        List<Treatment> listTreatment = treatmentRepository.findAll();
        if(listTreatment.isEmpty()){
            throw new RuntimeException("No se encontraron registros de Treatment en la base de datos.");
        }
        return treatmentUtils.convertListTreatmentDto(listTreatment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TreatmentDto> getTreatmentByIdClinic(Long idClinic){
        List<Treatment> treatment = treatmentRepository.findByIdClinic(idClinic);
        if (treatment.isEmpty()) {
            throw new RuntimeException("No se encontraron registros de Tratamiento en la base de datos.");
        }
        return treatmentUtils.convertListTreatmentDto(treatment);
    }
}

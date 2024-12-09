package com.beysa.services.UserDomain.Treatment;

import com.beysa.services.UserDomain.Treatment.DTO.TreatmentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TreatmentUtils {
    public TreatmentDto convertTreatmentDto(Treatment treatment){
        TreatmentDto response = new TreatmentDto();
        response.setIdTreatment(treatment.getIdTreatment());
        response.setName(treatment.getName());
        response.setDescription(treatment.getDescription());
        response.setCreateAd(treatment.getCreateAd());
        response.setIdClinic(treatment.getClinic().getIdClinic());
        response.setStatus(treatment.getStatus());
        return response;
    }

    public List<TreatmentDto> convertListTreatmentDto(List<Treatment> listTreatment){
        return listTreatment.stream()
                .map(this::convertTreatmentDto)
                .collect(Collectors.toList());
    }
}

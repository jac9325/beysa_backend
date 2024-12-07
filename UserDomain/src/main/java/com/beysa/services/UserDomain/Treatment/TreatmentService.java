package com.beysa.services.UserDomain.Treatment;

import com.beysa.services.UserDomain.Treatment.DTO.TreatmentDto;

import java.util.List;

public interface TreatmentService {
    TreatmentDto getTreatmentById(Long idTreatment);
    List<TreatmentDto> getAllTreatment();
    List<TreatmentDto> getTreatmentByIdClinic(Long idClinic);
}

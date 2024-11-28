package com.beysa.services.UserDomain.Medic;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;

import java.util.List;

public interface MedicService {
    MedicDto getMedicById(Long idMedic);
    List<MedicDto> getAllMedic();
    Boolean deleteMedic(Long idMedic);
    MedicDto saveMedic(Medic medic);
    Boolean updateMedic(MedicDto newMedic);
    Boolean updateSignatureMedic(Long idMedic, String pathSignature);
}

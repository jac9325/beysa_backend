package com.beysa.services.UserDomain.Medic;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;
import com.beysa.services.UserDomain.Shared.ServerImage.StorageService;
import com.beysa.services.UserDomain.Speciality.Speciality;
import com.beysa.services.UserDomain.Speciality.SpecialityService;
import com.beysa.services.UserDomain.Staff.Staff;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

@Service
public class MedicServiceImpl implements MedicService{

    private final MedicRepository medicRepository;
    private final MedicUtils medicUtils;
    private final SpecialityService specialityService;
    private final StorageService storageService;

    public MedicServiceImpl(MedicRepository medicRepository,
    MedicUtils medicUtils,
    SpecialityService specialityService,
    StorageService storageService){
        this.medicRepository = medicRepository;
        this.medicUtils = medicUtils;
        this.specialityService = specialityService;
        this.storageService = storageService;
    }

    @Transactional
    @Override
    public MedicDto saveMedic(Medic medic){
        try {
            medic = medicRepository.save(medic);
            if (medic.getIdMedic()<= 0){
                throw new RuntimeException("Error al guardar");
            }
            MedicDto medicResponse = medicUtils.convertMedicDto(medic);
            return medicResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Boolean updateMedic(MedicDto newMedic){
        try {
            Boolean response = false;
            if (newMedic == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el Médico");
            }
            Medic oldMedic = medicRepository.findById(newMedic.getIdMedic()).orElse(null);
            if (oldMedic == null){
                throw new RuntimeException("No se ha encontrado al Médico");
            }else{
                Speciality currentSpeciality = specialityService.getSpeciality(newMedic.getIdSpeciality());
                if (currentSpeciality == null){
                    throw new RuntimeException("No se ha encontrado la Especialidad requerida");
                }
                oldMedic.setSpeciality(currentSpeciality);
                oldMedic.setProfessionalLicenseNumber(newMedic.getProfessionalLicenseNumber());
                oldMedic.setUpdateAd(LocalDateTime.now());
                oldMedic.setSloganMedic(newMedic.getSloganMedic());
                medicRepository.save(oldMedic);
                response = true;
            }
           return response; 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public MedicDto getMedicById(Long idMedic){
        return medicRepository.findById(idMedic)
                .map(medicUtils::convertMedicDto)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado por el id: " + idMedic));
    }

    @Transactional(readOnly = true)
    @Override
    public List<MedicDto> getAllMedic(){
        List<Medic> listMedic = medicRepository.findAll();
        if(listMedic.isEmpty()){
            throw new RuntimeException("No se encontraron registros de Medico en la base de datos.");
        }
        return medicUtils.convertListMedicDto(listMedic);
    }

    @Transactional
    @Override
    public Boolean deleteMedic(Long idMedic){
        return medicRepository.findById(idMedic)
                .map(medic -> {
                    try {
                        medicRepository.delete(medic);
                        return true;
                    } catch (Exception e) {throw new RuntimeException("Error durante la operación de eliminar: " + e.getMessage(), e);}
                })
                .orElseThrow(() -> new RuntimeException("Medico no encontrado por el id: " + idMedic));
    }

    @Transactional
    @Override
    public Boolean updateSignatureMedic(Long idMedic, String pathSignature){
        try {
            Medic currentMedic = medicRepository.findById(idMedic).orElse(null);
            if (currentMedic == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el Médico");
            }
            currentMedic.setSignature(pathSignature);
            medicRepository.save(currentMedic);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    } 
}

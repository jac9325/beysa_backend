package com.beysa.services.UserDomain.Medic;

import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;
import com.beysa.services.UserDomain.Speciality.Speciality;
import com.beysa.services.UserDomain.Speciality.SpecialityService;

import java.util.List;

@Service
public class MedicServiceImpl implements MedicService{

    private final MedicRepository medicRepository;
    private final MedicUtils medicUtils;
    private final SpecialityService specialityService;

    public MedicServiceImpl(MedicRepository medicRepository,
    MedicUtils medicUtils,
    SpecialityService specialityService){
        this.medicRepository = medicRepository;
        this.medicUtils = medicUtils;
        this.specialityService = specialityService;
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

    /*@Transactional
    @Override
    public MedicDto createMedic(MedicDto request){
        if(request == null) throw new RuntimeException("Medico es nulo");
        try {
            Medic medic = convertMedicEntity(request);
            medicRepository.save(medic);
            return medicUtils.convertMedicDto(medic);
        }catch (Exception e){ throw new RuntimeException("Error durante la operación de crear: " + e.getMessage(), e);}
    }*/

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

    // @Transactional
    // @Override
    // public MedicDto updateMedic(Long idMedic, MedicDto request) {
    //     return medicRepository.findById(idMedic)
    //             .map(existingMedic -> {
    //                 try {
    //                     Medic newRequest = medicUtils.convertMedicEntity(request);
    //                     existingMedic.setSpeciality(newRequest.getSpeciality());
    //                     existingMedic.setStaff(newRequest.getStaff());
    //                     existingMedic.setProfessionalLicenseNumber(newRequest.getProfessionalLicenseNumber());
    //                     existingMedic.setCreateAd(newRequest.getCreateAd());
    //                     existingMedic.setUpdateAd(newRequest.getUpdateAd());
    //                     Medic updatedMedic = medicRepository.save(existingMedic);
    //                     return medicUtils.convertMedicDto(updatedMedic);
    //                 } catch (Exception e){throw new RuntimeException("Error during update operation: " + e.getMessage(), e);}
    //             })
    //             .orElseThrow(() -> new RuntimeException("Medic not found for id: " + idMedic));
    // }

    /*public Medic convertMedicEntity(MedicDto medic){
        Medic response = new Medic();
        response.setIdMedic(medic.getIdMedic());
        Staff staff = staffService.getStaffByIdEntity(medic.getIdStaff());
        response.setStaff(staff);
        Speciality speciality = specialityService.getSpecialityByIdEntity(medic.getIdSpeciality());
        response.setSpeciality(speciality);
        response.setProfessionalLicenseNumber(medic.getProfessionalLicenseNumber());
        response.setCreateAd(medic.getCreateAd());
        response.setUpdateAd(medic.getUpdateAd());
        response.setStatus(medic.getStatus());
        return response;
    }*/
}

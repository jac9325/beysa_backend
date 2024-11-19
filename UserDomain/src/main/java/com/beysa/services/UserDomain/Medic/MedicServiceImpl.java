package com.beysa.services.UserDomain.Medic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;

@Service
public class MedicServiceImpl implements MedicService{



    private final MedicRepository medicRepository;
    private final MedicUtils medicUtils;

    public MedicServiceImpl(MedicRepository medicRepository,
    MedicUtils medicUtils){
        this.medicRepository = medicRepository;
        this.medicUtils = medicUtils;
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

    // @Transactional(readOnly = true)
    // @Override
    // public MedicDto getMedicById(Long idMedic){
    //     return medicRepository.findById(idMedic)
    //             .map(medicUtils::convertMedicDto)
    //             .orElseThrow(() -> new RuntimeException("Medic not found for id: " + idMedic));
    // }

    // @Transactional(readOnly = true)
    // @Override
    // public List<MedicDto> getAllMedic(){
    //     List<Medic> listMedic = medicRepository.findAll();
    //     if(listMedic.isEmpty()){
    //         throw new RuntimeException("No Medic records found in the database");
    //     }
    //     return medicUtils.convertListMedicDto(listMedic);
    // }

    // @Transactional
    // @Override
    // public MedicDto createMedic(MedicDto request){
    //     if(request == null) throw new RuntimeException("MedicDto is null");
    //     try {
    //         Medic medic = medicUtils.convertMedicEntity(request);
    //         medicRepository.save(medic);
    //         return medicUtils.convertMedicDto(medic);
    //     }catch (Exception e){ throw new RuntimeException("Error during create operation: " + e.getMessage(), e);}

    // }

    // @Transactional
    // @Override
    // public Boolean deleteMedic(Long idMedic){
    //     return medicRepository.findById(idMedic)
    //             .map(medic -> {
    //                 try {
    //                     medicRepository.delete(medic);
    //                     return true;
    //                 } catch (Exception e) {throw new RuntimeException("Error during delete operation: " + e.getMessage(), e);}
    //             })
    //             .orElseThrow(() -> new RuntimeException("Personal not found for id: " + idMedic));
    // }

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

    // public Medic convertMedicEntity(MedicDto medic){
    //     Medic response = new Medic();
    //     response.setIdMedic(medic.getIdMedic());
    //     Staff staff = staffRepository.findById(medic.getIdMedic())
    //             .orElseThrow(() -> new RuntimeException("Staff not found for id: " + medic.getIdStaff()));
    //     response.setStaff(staff);
    //     Speciality speciality = specialityRepository.findById(medic.getIdSpeciality())
    //             .orElseThrow(() -> new RuntimeException("Speciality not found for id: " + medic.getIdSpeciality()));
    //     response.setSpeciality(speciality);
    //     response.setProfessionalLicenseNumber(medic.getProfessionalLicenseNumber());
    //     response.setCreateAd(medic.getCreateAd());
    //     response.setUpdateAd(medic.getUpdateAd());
    //     response.setStatus(medic.getStatus());
    //     return response;
    // }
}

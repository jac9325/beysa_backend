package com.beysa.services.UserDomain.Medic;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.beysa.services.UserDomain.Speciality.Speciality;
import com.beysa.services.UserDomain.Speciality.SpecialityRepository;
import com.beysa.services.UserDomain.Staff.Staff;
import com.beysa.services.UserDomain.Staff.StaffRepository;
import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Medic.DTO.MedicDto;

@Component
public class MedicUtils {
    private final StaffRepository staffRepository;
    private final SpecialityRepository specialityRepository;
    public MedicUtils(StaffRepository staffRepository, SpecialityRepository specialityRepository){
        this.staffRepository = staffRepository;
        this.specialityRepository = specialityRepository;
    }

    public MedicDto convertMedicDto(Medic medic) {
        if (medic == null) return null;
        MedicDto dto = new MedicDto();
        dto.setIdMedic(medic.getIdMedic());
        dto.setIdSpeciality(medic.getSpeciality().getIdSpeciality());
        dto.setIdStaff(medic.getStaff().getIdStaff());
        dto.setProfessionalLicenseNumber(medic.getProfessionalLicenseNumber());
        dto.setCreateAd(medic.getCreateAd());
        dto.setUpdateAd(medic.getUpdateAd());
        dto.setStatus(medic.getStatus());
        return dto;
    }

    public List<MedicDto> convertListMedicDto(List<Medic> medicList) {
        return medicList.stream()
                        .map(this::convertMedicDto)
                        .collect(Collectors.toList());
    }

    public Medic convertMedicEntity(MedicDto medic){
        Medic response = new Medic();
        response.setIdMedic(medic.getIdMedic());
        Staff staff = staffRepository.findById(medic.getIdMedic())
                .orElseThrow(() -> new RuntimeException("Staff not found for id: " + medic.getIdStaff()));
        response.setStaff(staff);
        Speciality speciality = specialityRepository.findById(medic.getIdSpeciality())
                .orElseThrow(() -> new RuntimeException("Speciality not found for id: " + medic.getIdSpeciality()));
        response.setSpeciality(speciality);
        response.setProfessionalLicenseNumber(medic.getProfessionalLicenseNumber());
        response.setCreateAd(medic.getCreateAd());
        response.setUpdateAd(medic.getUpdateAd());
        response.setStatus(medic.getStatus());
        return response;
    }
}

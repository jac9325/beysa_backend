package com.beysa.services.UserDomain.Staff;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.Clinic.Clinic;
import com.beysa.services.UserDomain.Clinic.ClinicService;
import com.beysa.services.UserDomain.Country.Country;
import com.beysa.services.UserDomain.Country.CountryService;
import com.beysa.services.UserDomain.Department.Department;
import com.beysa.services.UserDomain.Department.DepartmentService;
import com.beysa.services.UserDomain.Medic.Medic;
import com.beysa.services.UserDomain.Medic.MedicService;
import com.beysa.services.UserDomain.Medic.DTO.MedicDto;
import com.beysa.services.UserDomain.Province.Province;
import com.beysa.services.UserDomain.Province.ProvinceService;
import com.beysa.services.UserDomain.Rol.ERole;
import com.beysa.services.UserDomain.Rol.Rol;
import com.beysa.services.UserDomain.Rol.RolService;
import com.beysa.services.UserDomain.Speciality.Speciality;
import com.beysa.services.UserDomain.Speciality.SpecialityService;
import com.beysa.services.UserDomain.District.District;
import com.beysa.services.UserDomain.District.DistrictService;
import com.beysa.services.UserDomain.GeographicalLocation.GeographicalLocation;
import com.beysa.services.UserDomain.GeographicalLocation.GeographicalLocationService;
import com.beysa.services.UserDomain.GeographicalLocation.GeographicalLocationServiceImpl;
import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;
import com.beysa.services.UserDomain.IdentityDocument.IdentityDocument;
import com.beysa.services.UserDomain.IdentityDocument.IdentityDocumentService;
import com.beysa.services.UserDomain.Staff.DTO.StaffDto;
import com.beysa.services.UserDomain.Staff.DTO.StaffMedic;
import com.beysa.services.UserDomain.User.UserEntity;
import com.beysa.services.UserDomain.User.UserService;

@Service
public class StaffServiceImpl implements StaffService{

    private final MedicService medicService;
    private final CountryService countryService;
    private final DepartmentService departmentService;
    private final ProvinceService provinceService;
    private final DistrictService districtService;
    private final GeographicalLocationService geographicalLocationService;
    private final UserService userService;
    private final IdentityDocumentService identityDocumentService;
    private final StaffRepository staffRepository;
    private final SpecialityService specialityService;
    private final ClinicService clinicService;
    private final RolService rolService;

    public StaffServiceImpl(
        MedicService medicService,
        CountryService countryService,
        DepartmentService departmentService,
        ProvinceService provinceService,
        DistrictService districtService,
        GeographicalLocationService geographicalLocationService,
        UserService userService,
        IdentityDocumentService identityDocumentService,
        StaffRepository staffRepository,
        SpecialityService specialityService,
        ClinicService clinicService,
        RolService rolService
        ){
        this.medicService = medicService;
        this.countryService = countryService;
        this.departmentService = departmentService;
        this.provinceService = provinceService;
        this.districtService = districtService;
        this.geographicalLocationService = geographicalLocationService;
        this.userService = userService;
        this.identityDocumentService = identityDocumentService;
        this.staffRepository = staffRepository;
        this.specialityService = specialityService;
        this.clinicService = clinicService;
        this.rolService = rolService;
    }

    @Transactional
    public StaffMedic registerNewMedic(StaffMedic staffMedic){
        try {
            GeographicalLocationDto currentGeo = staffMedic.getGeographicalLocation();
            UserEntity currentUser = staffMedic.getUser();
            StaffDto staffDto = staffMedic.getStaff();
            MedicDto medicDto = staffMedic.getMedic();
            Clinic currentClinic = clinicService.getClinicByIdEntity(staffMedic.getIdClinic());
            if (currentClinic == null){
                throw new RuntimeException("Ha ocurrido un error al obtener la Clínica");
            }
            
            /*Handle GeographicalLocation */
            Country currentCountry = countryService.getCountryById(currentGeo.getIdCountry());
            if (currentCountry == null){
                throw new RuntimeException("Ha ocurrido un error al Obtener el Pais");
            }

            Department currentDepartment = departmentService.getDepartmentById(currentGeo.getIdDepartment());
            if (currentDepartment == null){
                throw new RuntimeException("Ha ocurrido un error al Obtener el Departamento");
            }

            Province currentProvince = provinceService.getProvinceByIdEntity(currentGeo.getIdProvince());
            if (currentProvince == null){
                throw new RuntimeException("Ha ocurrido un error al Obtener la Provincia");
            }

            District currentDistrict = districtService.getDistrictByIdEntity(currentGeo.getIdCountry());
            if (currentDistrict == null){
                throw new RuntimeException("Ha ocurrido un error al Obtener el Distrito");
            }

            GeographicalLocation currentGeographicalLocation = new GeographicalLocation();
            currentGeographicalLocation.setCountry(currentCountry);
            currentGeographicalLocation.setDepartment(currentDepartment);
            currentGeographicalLocation.setProvince(currentProvince);
            currentGeographicalLocation.setDistrict(currentDistrict);
            currentGeographicalLocation.setStatus(1);
            GeographicalLocationDto geoResponse= geographicalLocationService.saveGeoGraphical(currentGeographicalLocation);
            if (currentGeographicalLocation.getIdGeographicalLocation() <= 0){
                throw new RuntimeException("Ha ocurrido un error al guardar la Ubicación Geográfica");
            }

            /*Handle User */
            List<Rol> currentRols = new ArrayList<Rol>();
            String rolName = ERole.ROLE_MEDIC.toString();
            Rol currentRol = rolService.getRolByName(rolName);
            currentRols.add(currentRol);
            currentUser = userService.createUserAll(currentUser, currentRols, currentClinic);
            if (currentUser.getId_user() <= 0){
                throw new RuntimeException("Ha ocurrido un error al guardar el Usuario");
            }


            /* Handle Staff */
            IdentityDocument currentDocument = identityDocumentService.getIdentityDocumentById(staffDto.getIdIdentityDocument());
            if (currentDocument == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el Documento de Identidad");
            }

            Staff currentStaff = new Staff();
            currentStaff.setIdStaff(staffDto.getIdStaff());
            currentStaff.setName(staffDto.getName());
            currentStaff.setLastName(staffDto.getLastName());
            currentStaff.setDateOfBirth(staffDto.getDateOfBirth());
            currentStaff.setGender(staffDto.getGender());
            currentStaff.setIdentityDocument(currentDocument);
            currentStaff.setDocumentNumber(staffDto.getDocumentNumber());
            currentStaff.setMobileNumber(staffDto.getMobileNumber());
            currentStaff.setEmail(staffDto.getEmail());
            currentStaff.setAddress(staffDto.getAddress());
            currentStaff.setImage(staffDto.getImage());
            currentStaff.setDateEntry(staffDto.getDateEntry());
            currentStaff.setGeographicalLocation(currentGeographicalLocation);
            currentStaff.setSalary(staffDto.getSalary());
            currentStaff.setContractType(staffDto.getContractType());
            currentStaff.setStatus(staffDto.getStatus());
            currentStaff.setTypeStaff(StaffEnum.TYPE_MEDIC);
            currentStaff = staffRepository.save(currentStaff);
            currentStaff.setUser(currentUser);
            if (currentStaff.getIdStaff()<=0){
                throw new RuntimeException("Ha ocurrido un error al guardar el Empleado");
            }

            /* Handle */
            Speciality speciality = specialityService.getSpeciality(medicDto.getIdSpeciality());
            if (speciality == null){
                throw new RuntimeException("Ha ocurrido un error al obtener la Especialidad del Médico");
            }
            Medic currentMedic = new Medic();
            currentMedic.setIdMedic(medicDto.getIdMedic());
            currentMedic.setSpeciality(speciality);
            currentMedic.setStaff(currentStaff);
            currentMedic.setProfessionalLicenseNumber(medicDto.getProfessionalLicenseNumber());
            currentMedic.setCreateAd(medicDto.getCreateAd());
            currentMedic.setUpdateAd(medicDto.getUpdateAd());
            currentMedic.setStatus(1);
            MedicDto currentmedicResponse = medicService.saveMedic(currentMedic);
            StaffMedic currentStaffResponse = new StaffMedic();
            currentStaffResponse.setGeographicalLocation(geoResponse);
            currentStaffResponse.setMedic(currentmedicResponse);
            currentStaffResponse.setStaff(staffDto);
            return currentStaffResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

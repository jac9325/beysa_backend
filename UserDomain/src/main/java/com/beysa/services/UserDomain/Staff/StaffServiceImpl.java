package com.beysa.services.UserDomain.Staff;

import java.math.BigDecimal;
import java.security.Permission;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beysa.services.UserDomain.Admin.AdminEntity;
import com.beysa.services.UserDomain.Admin.AdminService;
import com.beysa.services.UserDomain.Admin.DTO.AdminDtos;
import com.beysa.services.UserDomain.Clinic.Clinic;
import com.beysa.services.UserDomain.Clinic.ClinicService;
import com.beysa.services.UserDomain.Collaborator.Collaborator;
import com.beysa.services.UserDomain.Collaborator.CollaboratorService;
import com.beysa.services.UserDomain.Collaborator.DTO.CollaboratorDto;
import com.beysa.services.UserDomain.Country.Country;
import com.beysa.services.UserDomain.Country.CountryService;
import com.beysa.services.UserDomain.Department.Department;
import com.beysa.services.UserDomain.Department.DepartmentService;
import com.beysa.services.UserDomain.Medic.Medic;
import com.beysa.services.UserDomain.Medic.MedicService;
import com.beysa.services.UserDomain.Medic.DTO.MedicDto;
import com.beysa.services.UserDomain.Permissions.PermissionsEntity;
import com.beysa.services.UserDomain.Permissions.PermissionsService;
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
import com.beysa.services.UserDomain.Staff.DTO.StaffAdmin;
import com.beysa.services.UserDomain.Staff.DTO.StaffCollaborator;
import com.beysa.services.UserDomain.Staff.DTO.StaffDto;
import com.beysa.services.UserDomain.Staff.DTO.StaffMedic;
import com.beysa.services.UserDomain.User.UserEntity;
import com.beysa.services.UserDomain.User.UserService;
import com.beysa.services.UserDomain.UserPermissions.UserPermissions;
import com.beysa.services.UserDomain.UserPermissions.UserPermissionsService;
import com.beysa.services.UserDomain.UserPermissions.DTO.UserPermissionsDto;

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
    private final CollaboratorService collaboratorService;
    private final StaffUtils staffUtils;
    private final AdminService adminService;
    private final UserPermissionsService userPermissionsService;
    private final PermissionsService permissionsService;

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
        RolService rolService,
        CollaboratorService collaboratorService,
        StaffUtils staffUtils,
        AdminService adminService,
        UserPermissionsService userPermissionsService,
        PermissionsService permissionsService
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
        this.collaboratorService = collaboratorService;
        this.staffUtils = staffUtils;
        this.adminService = adminService;
        this.userPermissionsService = userPermissionsService;
        this.permissionsService = permissionsService;
    }

    @Transactional
    @Override
    public StaffMedic registerNewMedic(StaffMedic staffMedic){
        try {
            GeographicalLocationDto currentGeo = staffMedic.getGeographicalLocation();
            UserEntity currentUser = staffMedic.getUser();
            StaffDto staffDto = staffMedic.getStaff();
            MedicDto medicDto = staffMedic.getMedic();
            Clinic currentClinic = clinicService.getClinicByIdEntity(staffMedic.getIdClinic());
            List<UserPermissionsDto> currentListPermissions = staffMedic.getUserPermissions();
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

            /* Handle Permissions */
            if (currentListPermissions.size() > 0){
                for (UserPermissionsDto userPermissions : currentListPermissions) {
                    PermissionsEntity currentPermission = permissionsService.getPermissionById(userPermissions.getIdPermissions());
                    UserPermissions currentSaveUserPer = new UserPermissions();
                    currentSaveUserPer.setIdUserPermissions(0L);
                    currentSaveUserPer.setPermissions(currentPermission);
                    currentSaveUserPer.setUser(currentUser);
                    currentSaveUserPer.setStatus(1);
                    userPermissionsService.save(currentSaveUserPer);
                }
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
            StaffDto staffdtoResponse= staffUtils.convertStaffDto(currentStaff);

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
            currentStaffResponse.setStaff(staffdtoResponse);
            return currentStaffResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public StaffCollaborator registerNewCollaborator(StaffCollaborator staffCollaborator){
        try {
            GeographicalLocationDto currentGeo = staffCollaborator.getGeographicalLocation();
            UserEntity currentUser = staffCollaborator.getUser();
            StaffDto staffDto = staffCollaborator.getStaff();
            CollaboratorDto collaboratorDto = staffCollaborator.getCollaborator();
            List<UserPermissionsDto> currentListPermissions = staffCollaborator.getUserPermissions();
            Clinic currentClinic = clinicService.getClinicByIdEntity(staffCollaborator.getIdClinic());
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
            String rolName = ERole.ROLE_COLLABORATOR.toString();
            Rol currentRol = rolService.getRolByName(rolName);
            currentRols.add(currentRol);
            currentUser = userService.createUserAll(currentUser, currentRols, currentClinic);
            if (currentUser.getId_user() <= 0){
                throw new RuntimeException("Ha ocurrido un error al guardar el Usuario");
            }

            /* Handle Permissions */
            if (currentListPermissions.size() > 0){
                for (UserPermissionsDto userPermissions : currentListPermissions) {
                    PermissionsEntity currentPermission = permissionsService.getPermissionById(userPermissions.getIdPermissions());
                    UserPermissions currentSaveUserPer = new UserPermissions();
                    currentSaveUserPer.setIdUserPermissions(0L);
                    currentSaveUserPer.setPermissions(currentPermission);
                    currentSaveUserPer.setUser(currentUser);
                    currentSaveUserPer.setStatus(1);
                    userPermissionsService.save(currentSaveUserPer);
                }
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
            currentStaff.setTypeStaff(StaffEnum.TYPE_COLLABORATOR);
            currentStaff = staffRepository.save(currentStaff);
            currentStaff.setUser(currentUser);
            if (currentStaff.getIdStaff()<=0){
                throw new RuntimeException("Ha ocurrido un error al guardar el Empleado");
            }
            StaffDto staffdtoResponse= staffUtils.convertStaffDto(currentStaff);

            /* Handle */
            Collaborator currentCollaborator = new Collaborator();
            currentCollaborator.setIdCollaborator(0L);
            currentCollaborator.setStaff(currentStaff);
            currentCollaborator.setLevelEducation(collaboratorDto.getLevelEducation());
            currentCollaborator.setStudyQualification(collaboratorDto.getStudyQualification());
            currentCollaborator.setCreateAd(collaboratorDto.getCreateAd());
            currentCollaborator.setUpdateAd(collaboratorDto.getUpdateAd());
            currentCollaborator.setStatus(collaboratorDto.getStatus());
            CollaboratorDto currentCollaboratorResponse = collaboratorService.saveCollaborator(currentCollaborator);
            StaffCollaborator currentStaffResponse = new StaffCollaborator();
            currentStaffResponse.setGeographicalLocation(geoResponse);
            currentStaffResponse.setCollaborator(currentCollaboratorResponse);
            currentStaffResponse.setStaff(staffdtoResponse);
            return currentStaffResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public StaffAdmin registerNewAdmin(StaffAdmin staffAdmin){
        try {
            GeographicalLocationDto currentGeo = staffAdmin.getGeographicalLocation();
            UserEntity currentUser = staffAdmin.getUser();
            StaffDto staffDto = staffAdmin.getStaff();
            AdminDtos adminDtos = staffAdmin.getAdmin();
            List<UserPermissionsDto> currentListPermissions = staffAdmin.getUserPermissions();
            Clinic currentClinic = clinicService.getClinicByIdEntity(staffAdmin.getIdClinic());
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
            String rolName = ERole.ROLE_ADMIN.toString();
            Rol currentRol = rolService.getRolByName(rolName);
            currentRols.add(currentRol);
            currentUser = userService.createUserAll(currentUser, currentRols, currentClinic);
            if (currentUser.getId_user() <= 0){
                throw new RuntimeException("Ha ocurrido un error al guardar el Usuario");
            }

            /* Handle Permissions */
            if (currentListPermissions.size() > 0){
                for (UserPermissionsDto userPermissions : currentListPermissions) {
                    PermissionsEntity currentPermission = permissionsService.getPermissionById(userPermissions.getIdPermissions());
                    UserPermissions currentSaveUserPer = new UserPermissions();
                    currentSaveUserPer.setIdUserPermissions(0L);
                    currentSaveUserPer.setPermissions(currentPermission);
                    currentSaveUserPer.setUser(currentUser);
                    currentSaveUserPer.setStatus(1);
                    userPermissionsService.save(currentSaveUserPer);
                }
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
            currentStaff.setTypeStaff(StaffEnum.TYPE_ADMIN);
            currentStaff = staffRepository.save(currentStaff);
            currentStaff.setUser(currentUser);
            if (currentStaff.getIdStaff()<=0){
                throw new RuntimeException("Ha ocurrido un error al guardar el Empleado");
            }
            StaffDto staffdtoResponse= staffUtils.convertStaffDto(currentStaff);

            /* Handle */
            AdminEntity currentAdmin = new AdminEntity();
            currentAdmin.setIdAdmin(adminDtos.getIdAdmin());
            currentAdmin.setStaff(currentStaff);
            currentAdmin.setBranchManager(adminDtos.getBranchManager());
            currentAdmin.setTypeAdmin(adminDtos.getTypeAdmin());
            currentAdmin.setCreateAd(adminDtos.getCreateAd());
            currentAdmin.setUpdateAd(adminDtos.getUpdateAd());
            currentAdmin.setStatus(adminDtos.getStatus());
            AdminDtos adminResponse = adminService.saveAdmin(currentAdmin);

           StaffAdmin currentStaffResponse = new StaffAdmin();
            currentStaffResponse.setGeographicalLocation(geoResponse);
            currentStaffResponse.setAdmin(adminResponse);
            currentStaffResponse.setStaff(staffdtoResponse);
            return currentStaffResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

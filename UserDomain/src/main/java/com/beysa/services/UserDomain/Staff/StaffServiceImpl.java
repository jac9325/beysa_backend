package com.beysa.services.UserDomain.Staff;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
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
            if (currentUser.getIdUser() <= 0){
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
            if (staffDto.getGender() == "M"){
                currentStaff.setImage("img-man-default.jpg");
            }else{
                currentStaff.setImage("img-woman-default.jpg");
            }
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
            currentMedic.setSignature("img-signature-default.png");
            currentMedic.setSloganMedic(medicDto.getSloganMedic());

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
            if (currentUser.getIdUser() <= 0){
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
            if (staffDto.getGender() == "M"){
                currentStaff.setImage("img-man-default.jpg");
            }else{
                currentStaff.setImage("img-woman-default.jpg");
            }
            currentStaff.setIdentityDocument(currentDocument);
            currentStaff.setDocumentNumber(staffDto.getDocumentNumber());
            currentStaff.setMobileNumber(staffDto.getMobileNumber());
            currentStaff.setEmail(staffDto.getEmail());
            currentStaff.setAddress(staffDto.getAddress());
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
            currentCollaborator.setSloganCollaborator(collaboratorDto.getSloganCollaborator());
            currentCollaborator.setSignature("img-signature-default.png");
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
            if (currentUser.getIdUser() <= 0){
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
            if (staffDto.getGender() == "M"){
                currentStaff.setImage("img-man-default.jpg");
            }else{
                currentStaff.setImage("img-woman-default.jpg");
            }
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
            currentAdmin.setSloganAdmin(adminDtos.getSloganAdmin());
            currentAdmin.setSignature("img-signature-default.png");
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

    @Transactional(readOnly = true)
    @Override
    public Staff getStaffByIdEntity(Long idStaff){
        return staffRepository.findById(idStaff)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado por el id: " + idStaff));
    }

    @Transactional
    @Override
    public Boolean updateStaff(StaffDto newStaff, GeographicalLocationDto newGeographicalLocation){
        try {
            if (newStaff == null){
                throw new RuntimeException("Ha ocurrido un error al actualizar el Personal");
            }
            GeographicalLocation oldGeographicalLocation = geographicalLocationService.getGeographicalLocationByIdEntity(newGeographicalLocation.getIdGeographicalLocation());
            if (oldGeographicalLocation == null){
                throw new RuntimeException("Ha ocurrido un error al obtener la Ubicación Geográfica");
            }
            Country currentCountry = countryService.getCountryById(newGeographicalLocation.getIdCountry());
            if (currentCountry == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el País");
            }
            Department currentDepartment = departmentService.getDepartmentById(newGeographicalLocation.getIdDepartment());
            if (currentDepartment == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el Departamento");
            }
            Province currentProvince = provinceService.getProvinceByIdEntity(newGeographicalLocation.getIdProvince());
            if (currentProvince == null){
                throw new RuntimeException("Ha ocurrido un error al obtener la Provincia");
            }
            District currentDistrict = districtService.getDistrictByIdEntity(newGeographicalLocation.getIdDistrict());
            if (currentDistrict == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el Distrito");
            }
            oldGeographicalLocation.setCountry(currentCountry);
            oldGeographicalLocation.setDepartment(currentDepartment);
            oldGeographicalLocation.setProvince(currentProvince);
            oldGeographicalLocation.setDistrict(currentDistrict);
            geographicalLocationService.saveGeoGraphical(oldGeographicalLocation);
            /**Handle Staff */
            Staff oldStaff = staffRepository.findById(newStaff.getIdStaff()).orElse(null);
            if (oldStaff == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el Personal");
            }
            IdentityDocument currentDocument = identityDocumentService.getIdentityDocumentById(newStaff.getIdIdentityDocument());
            if (currentDocument == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el Documento de Identidad");
            }
            oldStaff.setName(newStaff.getName());
            oldStaff.setLastName(newStaff.getLastName());
            oldStaff.setDateOfBirth(newStaff.getDateOfBirth());
            oldStaff.setGender(newStaff.getGender());
            oldStaff.setIdentityDocument(currentDocument);
            oldStaff.setMobileNumber(newStaff.getMobileNumber());
            oldStaff.setEmail(newStaff.getEmail());
            oldStaff.setAddress(newStaff.getAddress());
            oldStaff.setDateEntry(newStaff.getDateEntry());
            oldStaff.setGeographicalLocation(oldGeographicalLocation);
            oldStaff.setSalary(newStaff.getSalary());
            oldStaff.setContractType(newStaff.getContractType());
            staffRepository.save(oldStaff);
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Boolean updateImageStaff(Long idStaff, String pathImg){
        try {
            Staff currentStaff = staffRepository.findById(idStaff).orElse(null);
            if (currentStaff == null){
                throw new RuntimeException("Ha ocurrido un error al obtener el Personal");
            }
            currentStaff.setImage(pathImg);
            staffRepository.save(currentStaff);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

package com.beysa.services.UserDomain.Staff;

import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;
import com.beysa.services.UserDomain.Staff.DTO.StaffAdmin;
import com.beysa.services.UserDomain.Staff.DTO.StaffCollaborator;
import com.beysa.services.UserDomain.Staff.DTO.StaffDto;
import com.beysa.services.UserDomain.Staff.DTO.StaffMedic;
import com.beysa.services.UserDomain.User.DTO.UserChargeData;

public interface StaffService {
    StaffMedic registerNewMedic(StaffMedic staffMedic);
    StaffCollaborator registerNewCollaborator(StaffCollaborator staffCollaborator);
    StaffAdmin registerNewAdmin(StaffAdmin staffAdmin);
    Boolean updateStaff(StaffDto newStaff, GeographicalLocationDto newGeographicalLocation);
    Staff getStaffByIdEntity(Long idStaff);
    Boolean updateImageStaff(Long idStaff, String pathImg);
    UserChargeData chargeDataUser(String username);
}

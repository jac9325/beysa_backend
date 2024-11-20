package com.beysa.services.UserDomain.Staff.DTO;

import java.util.List;

import com.beysa.services.UserDomain.Collaborator.DTO.CollaboratorDto;
import com.beysa.services.UserDomain.GeographicalLocation.DTO.GeographicalLocationDto;
import com.beysa.services.UserDomain.User.UserEntity;
import com.beysa.services.UserDomain.UserPermissions.DTO.UserPermissionsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffCollaborator {
    StaffDto staff;
    CollaboratorDto collaborator;
    GeographicalLocationDto geographicalLocation;
    UserEntity user;
    Long idClinic;
    List<UserPermissionsDto> userPermissions;
}

package com.beysa.services.UserDomain.Collaborator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Collaborator.DTO.CollaboratorDto;

@Component
public class CollaboratorUtils {
    public CollaboratorDto convertCollaboratorDto(Collaborator collaborator) {
        if (collaborator == null) return null;
        CollaboratorDto dto = new CollaboratorDto();
        dto.setIdCollaborator(collaborator.getIdCollaborator());
        dto.setIdStaff(collaborator.getStaff().getIdStaff());
        dto.setLevelEducation(collaborator.getLevelEducation());
        dto.setStudyQualification(collaborator.getStudyQualification());
        dto.setCreateAd(collaborator.getCreateAd());
        dto.setUpdateAd(collaborator.getUpdateAd());
        dto.setStatus(collaborator.getStatus());
        dto.setSignature(collaborator.getSignature());
        dto.setSloganCollaborator(collaborator.getSloganCollaborator());
        return dto;
    }

    public List<CollaboratorDto> convertListCollaboratorDto(List<Collaborator> collaboratorList) {
        return collaboratorList.stream()
                               .map(this::convertCollaboratorDto)
                               .collect(Collectors.toList());
    }
}

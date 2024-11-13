package com.beysa.services.UserDomain.Collaborator;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.beysa.services.UserDomain.Collaborator.DTO.CollaboratorDtos;

@Component
public class CollaboratorUtils {
    public CollaboratorDtos convertCollaboratorDtos(Collaborator collaborator) {
        if (collaborator == null) {
            return null;
        }
        
        CollaboratorDtos dto = new CollaboratorDtos();
        dto.setIdCollaborator(collaborator.getIdCollaborator());
        dto.setIdStaff(collaborator.getStaff().getIdStaff());
        dto.setLevelEducation(collaborator.getLevelEducation());
        dto.setStudyQualification(collaborator.getStudyQualification());
        dto.setCreateAd(collaborator.getCreateAd());
        dto.setUpdateAd(collaborator.getUpdateAd());
        dto.setStatus(collaborator.getStatus());
        return dto;
    }

    public List<CollaboratorDtos> convertListCollaboratorDtos(List<Collaborator> collaboratorList) {
        return collaboratorList.stream()
                               .map(this::convertCollaboratorDtos)
                               .collect(Collectors.toList());
    }
}

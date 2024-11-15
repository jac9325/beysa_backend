package com.beysa.services.UserDomain.Collaborator;

import com.beysa.services.UserDomain.Collaborator.DTO.CollaboratorDto;

import java.util.List;

public interface CollaboratorService {
    CollaboratorDto getCollaboratorById(Long idCollaborator);
    List<CollaboratorDto> getAllCollaborator();
}

package com.beysa.services.UserDomain.Collaborator;

import com.beysa.services.UserDomain.Collaborator.DTO.CollaboratorDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollaboratorServiceImpl implements CollaboratorService{

    private final CollaboratorRepository collaboratorRepository;
    private final CollaboratorUtils collaboratorUtils;

    public CollaboratorServiceImpl(CollaboratorRepository collaboratorRepository, CollaboratorUtils collaboratorUtils){
        this.collaboratorRepository = collaboratorRepository;
        this.collaboratorUtils = collaboratorUtils;
    }

    @Transactional(readOnly = true)
    @Override
    public CollaboratorDto getCollaboratorById(Long idCollaborator){
        return collaboratorRepository.findById(idCollaborator)
                .map(collaboratorUtils::convertCollaboratorDto)
                .orElseThrow(() -> new RuntimeException("Collaborator not found for id: " + idCollaborator));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CollaboratorDto> getAllCollaborator(){
        List<Collaborator> listCollaborator = collaboratorRepository.findAll();
        if(listCollaborator.isEmpty()){
            throw new RuntimeException("No Collaborator records found in the database");
        }
        return collaboratorUtils.convertListCollaboratorDto(listCollaborator);
    }
}

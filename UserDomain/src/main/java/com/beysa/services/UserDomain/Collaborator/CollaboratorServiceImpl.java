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

    @Transactional
    @Override
    public CollaboratorDto saveCollaborator(Collaborator collaborator){
        try {
            collaborator = collaboratorRepository.save(collaborator);
            CollaboratorDto collaboratorResponse = collaboratorUtils.convertCollaboratorDto(collaborator);
            return collaboratorResponse;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CollaboratorDto getCollaboratorById(Long idCollaborator){
        return collaboratorRepository.findById(idCollaborator)
                .map(collaboratorUtils::convertCollaboratorDto)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado por el id: " + idCollaborator));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CollaboratorDto> getAllCollaborator(){
        List<Collaborator> listCollaborator = collaboratorRepository.findAll();
        if(listCollaborator.isEmpty()){
            throw new RuntimeException("No se encontraron registros de Colaborador en la base de datos.");
        }
        return collaboratorUtils.convertListCollaboratorDto(listCollaborator);
    }
}

package com.beysa.services.UserDomain.IdentityDocument;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdentityDocumentServiceImpl implements IdentityDocumentService{
    private final IdentityDocumentRepository identityDocumentRepository;

    @Override
    public IdentityDocument createIdentityDocument(IdentityDocument request) {
        if(request == null) throw new RuntimeException("Documento de identidad es nulo");
        try {
            return identityDocumentRepository.save(request);
        }catch (Exception e){ throw new RuntimeException("Error durante la operación de crear: " + e.getMessage(), e);}
    }

    @Transactional(readOnly = true)
    @Override
    public IdentityDocument getIdentityDocumentById(Long idIdentityDocument) {
        return identityDocumentRepository.findById(idIdentityDocument)
                .orElseThrow(() -> new RuntimeException("Documento de identidad no encontrado por el id: " + idIdentityDocument));
    }

    @Transactional(readOnly = true)
    @Override
    public List<IdentityDocument> getAllIdentityDocuments() {
        List<IdentityDocument> listIdentityDocument = identityDocumentRepository.findAll();
        if(listIdentityDocument.isEmpty()){
            throw new RuntimeException("No se encontraron registros de Documento de identidad en la base de datos.");
        }
        return listIdentityDocument;
    }
}

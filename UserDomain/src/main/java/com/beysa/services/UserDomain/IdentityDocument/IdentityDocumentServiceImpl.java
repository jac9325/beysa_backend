package com.beysa.services.UserDomain.IdentityDocument;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdentityDocumentServiceImpl implements IdentityDocumentService{

    private final IdentityDocumentRepository identityDocumentRepository;

    @Override
    public IdentityDocument createIdentityDocument(IdentityDocument request) {
        if(request == null) throw new RuntimeException("IdentityDocumentDto is null");
        try {
            return identityDocumentRepository.save(request);
        }catch (Exception e){ throw new RuntimeException("Error during create operation: " + e.getMessage(), e);}
    }

    @Override
    public IdentityDocument getIdentityDocumentById(Long idIdentityDocument) {
        return identityDocumentRepository.findById(idIdentityDocument)
                .orElseThrow(() -> new RuntimeException("IdentityDocument not found for id: " + idIdentityDocument));
    }

    @Override
    public List<IdentityDocument> getAllIdentityDocuments() {
        List<IdentityDocument> listIdentityDocument = identityDocumentRepository.findAll();
        if(listIdentityDocument.isEmpty()){
            throw new RuntimeException("No IdentityDocument records found in the database");
        }
        return listIdentityDocument;
    }
}

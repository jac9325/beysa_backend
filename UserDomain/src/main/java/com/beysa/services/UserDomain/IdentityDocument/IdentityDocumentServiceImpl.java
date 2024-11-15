package com.beysa.services.UserDomain.IdentityDocument;


import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class IdentityDocumentServiceImpl implements IdentityDocumentService{
    @Autowired
    private IdentityDocumentRepository identityDocumentRepository;

    @Override
    public IdentityDocument createIdentityDocument(IdentityDocument identityDocument) {
        return identityDocumentRepository.save(identityDocument);
    }

    @Override
    public IdentityDocument getIdentityDocumentById(Long id) {
        return identityDocumentRepository.findById(id).orElse(null);
    }

    @Override
    public List<IdentityDocument> getAllIdentityDocuments() {
        return identityDocumentRepository.findAll();
    }
}

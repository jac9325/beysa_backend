package com.beysa.services.UserDomain.IdentityDocument;

import java.util.List;

public interface IdentityDocumentService {
    IdentityDocument createIdentityDocument(IdentityDocument identityDocument);
    IdentityDocument getIdentityDocumentById(Long id);
    List<IdentityDocument> getAllIdentityDocuments();
}

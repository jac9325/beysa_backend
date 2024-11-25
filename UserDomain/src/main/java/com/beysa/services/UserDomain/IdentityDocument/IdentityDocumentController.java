package com.beysa.services.UserDomain.IdentityDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/v1/identity/document")

public class IdentityDocumentController {
    @Autowired
    private IdentityDocumentService identityDocumentService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @PostMapping("/create")
    public ResponseEntity<?> createIdentityDocument(@RequestBody IdentityDocument identityDocument) {
        try {
            IdentityDocument createdDocument = identityDocumentService.createIdentityDocument(identityDocument);
            return response.success(codes.created(), messages.created(), createdDocument, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIdentityDocumentById(@PathVariable Long id) {
        try {
            IdentityDocument document = identityDocumentService.getIdentityDocumentById(id);
            if (document == null) {
                return response.error(codes.notFound(), messages.notFound(), null);
            }
            return response.ok(codes.ok(), messages.ok(), document, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllIdentityDocuments() {
        try {
            List<IdentityDocument> documents = identityDocumentService.getAllIdentityDocuments();
            return response.ok(codes.ok(), messages.ok(), documents, null);
        } catch (Exception e) {
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}

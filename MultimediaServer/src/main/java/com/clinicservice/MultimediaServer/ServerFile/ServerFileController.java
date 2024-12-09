package com.clinicservice.MultimediaServer.ServerFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicservice.MultimediaServer.HttpResponse.Codes;
import com.clinicservice.MultimediaServer.HttpResponse.Messages;
import com.clinicservice.MultimediaServer.HttpResponse.ResponseUtils;

@RestController
@RequestMapping("/api/storage")
public class ServerFileController {
    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @Autowired
    ServerFileService serverFileService;

    @PostMapping("/create-folder/{idClinica}")
    public ResponseEntity<?> createFolder(@PathVariable String idClinica) {
        try {
            Boolean created = serverFileService.saveFolder(idClinica);
            return response.ok(codes.ok(), messages.ok(), created,null);
        } catch (Exception e) {
            return response.error(codes.error(), e.getMessage(),null);
        }
    }
}

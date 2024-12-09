package com.clinicservice.MultimediaServer.ServerFile;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServerFileService {
    @Value("${base.location.storage}")
    private String baseStoragePath;

    @Transactional
    public Boolean saveFolder(String idClinica){
        try {
            Boolean response = false;
            String base = "clinica";
            String baseMultimedia = "multimedia";
            String baseFiles = "documents";
            File folder = new File(baseStoragePath + File.separator + base +idClinica);
            if (!folder.exists()) {
                boolean created = folder.mkdirs();
                response =  true;
            } else {
                throw new RuntimeException("Error al crear la carpeta");
            }

            File folderMultimedia = new File(baseStoragePath + File.separator + base + idClinica + File.separator + baseMultimedia);
            if (!folderMultimedia.exists()) {
                boolean created = folderMultimedia.mkdirs();
                response =  true;
            } else {
                throw new RuntimeException("Error al crear la carpeta");
            }

            File folderFiles = new File(baseStoragePath + File.separator + base + idConsultorio + File.separator + baseFiles);           
            if (!folderFiles.exists()) {
                boolean created = folderFiles.mkdirs();
                response =  true;
            } else {
                throw new RuntimeException("Error al crear la carpeta");
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

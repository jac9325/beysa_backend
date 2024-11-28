package com.beysa.services.UserDomain.Shared.ServerImage;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class StorageServiceImpl implements StorageService {

    @Value("${media.location.image}")
    private String imageLocation;

    @Override
    @PostConstruct
    public void init() throws Exception {
        imagePath = Paths.get(imageLocation);
        Files.createDirectories(imagePath);
    }

    private Path imagePath;

    @Override
    public String store(MultipartFile file) {
        try {
            String defaultImage = "img-man-default.jpg";
            if (file.isEmpty()) {
                return defaultImage;
            }

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null) {
                throw new RuntimeException("Original file name is null");
            }
            
            String uniqueFileName = UUID.randomUUID().toString() +
                    originalFileName.substring(originalFileName.lastIndexOf("."));
            Path destinationFile = imagePath.resolve(Paths.get(uniqueFileName))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            if (fileName == null || fileName.isEmpty()) {
                throw new RuntimeException("Filename is empty");
            }
            Path file = imagePath.resolve(fileName);
            Resource resource = new UrlResource((file.toUri()));
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + fileName);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            Path file = imagePath.resolve(fileName).normalize().toAbsolutePath();
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file: " + fileName, e);
        }
    }
}

package com.beysa.services.UserDomain.Shared.ServerImage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init() throws Exception;

    String store(MultipartFile file);

    Resource loadAsResource(String filename);

    void delete(String filename);
}
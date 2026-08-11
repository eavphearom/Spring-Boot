package com.example.tutorial.service.impl;

import com.example.tutorial.enums.ImageDirectory;
import com.example.tutorial.service.MediaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

    @Value("${app.upload.image-path}")
    private String imageUploadPath;

    @Override
    public String uploadImage(
            MultipartFile file,
            ImageDirectory directory
    ) {
        try {
            // Get original extension
            String originalName = file.getOriginalFilename();

            String extension = "";
            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }

            // Generate unique filename
            String fileName = UUID.randomUUID() + extension;

            // uploads/images/profile
            Path folderPath = Paths.get(
                    imageUploadPath,
                    directory.getDirectory()
            );

            // Create folder if it doesn't exist
            Files.createDirectories(folderPath);

            // uploads/images/profile/uuid.jpg
            Path filePath = folderPath.resolve(fileName);

            // Save image
            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            // Store this value in DB
            return directory.getDirectory() + "/" + fileName;

        } catch (IOException exception) {
            throw new RuntimeException("Failed to upload image");
        }
    }

    @Override
    public String getImageUrl(String filePath) {

        if (filePath == null || filePath.isBlank()) {
            return null;
        }

        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/uploads/images/")
                .path(filePath)
                .toUriString();
    }

    @Override
    public void deleteImage(String filePath) {

        if (filePath == null || filePath.isBlank()) {
            return;
        }

        try {
            Path path = Paths.get(imageUploadPath, filePath);

            Files.deleteIfExists(path);

        } catch (IOException exception) {
            throw new RuntimeException("Failed to delete image");
        }
    }
}
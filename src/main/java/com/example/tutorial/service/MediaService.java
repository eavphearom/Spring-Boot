package com.example.tutorial.service;

import com.example.tutorial.enums.ImageDirectory;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    String uploadImage(MultipartFile file, ImageDirectory directory);

    void deleteImage(String fileName);
    String getImageUrl(String fileName);
}
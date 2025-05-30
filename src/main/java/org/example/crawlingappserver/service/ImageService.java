package org.example.crawlingappserver.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImageService {
    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");

    public String getFirstImageBase64(String imagePath) {
        File dir = new File(imagePath);
        if (!dir.exists() || !dir.isDirectory()) return null;
        File[] files = dir.listFiles();
        if (files == null) return null;

        Optional<File> firstImage = Arrays.stream(files)
                .filter(file -> {
                    String name = file.getName().toLowerCase();
                    return SUPPORTED_EXTENSIONS.stream().anyMatch(name::endsWith);
                })
                .findFirst();

        if (firstImage.isEmpty()) return null;

        try {
            byte[] imageBytes = Files.readAllBytes(firstImage.get().toPath());
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            return null;
        }
    }

    public List<String> getAllImagesBase64(String imagePath) {
        File dir = new File(imagePath);
        if (!dir.exists() || !dir.isDirectory()) return List.of();
        File[] files = dir.listFiles();
        if (files == null) return List.of();

        return Arrays.stream(files)
                .filter(file -> {
                    String name = file.getName().toLowerCase();
                    return SUPPORTED_EXTENSIONS.stream().anyMatch(name::endsWith);
                })
                .map(file -> {
                    try {
                        byte[] imageBytes = Files.readAllBytes(file.toPath());
                        return Base64.getEncoder().encodeToString(imageBytes);
                    } catch (IOException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}

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
    private static final String STATIC_RESOURCE_PATH = "src/main/resources/static";

    public String getFirstImageBase64(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return null;
        }

        // DB 경로를 실제 파일 시스템 경로로 변환
        String actualPath = STATIC_RESOURCE_PATH + "/" + imagePath;
        File dir = new File(actualPath);

        // 디렉토리가 존재하지 않는 경우 대안 경로 시도
        if (!dir.exists() || !dir.isDirectory()) {
            // knu_it를 knu_cse로 변경하여 시도
            String alternativePath = imagePath.replace("knu_it", "knu_cse");
            dir = new File(STATIC_RESOURCE_PATH + "/" + alternativePath);

            if (!dir.exists() || !dir.isDirectory()) {
                System.err.println("Directory not found: " + actualPath + " and " + STATIC_RESOURCE_PATH + "/" + alternativePath);
                return null;
            }
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }

        Optional<File> firstImage = Arrays.stream(files)
                .filter(file -> {
                    String name = file.getName().toLowerCase();
                    return SUPPORTED_EXTENSIONS.stream().anyMatch(name::endsWith);
                })
                .findFirst();

        if (firstImage.isEmpty()) {
            System.err.println("No image files found in directory: " + dir.getAbsolutePath());
            return null;
        }

        try {
            byte[] imageBytes = Files.readAllBytes(firstImage.get().toPath());
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            System.err.println("Failed to read image file: " + firstImage.get().getAbsolutePath() + " - " + e.getMessage());
            return null;
        }
    }

    public List<String> getAllImagesBase64(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return List.of();
        }

        // DB 경로를 실제 파일 시스템 경로로 변환
        String actualPath = STATIC_RESOURCE_PATH + "/" + imagePath;
        File dir = new File(actualPath);

        // 디렉토리가 존재하지 않는 경우 대안 경로 시도
        if (!dir.exists() || !dir.isDirectory()) {
            // knu_it를 knu_cse로 변경하여 시도
            String alternativePath = imagePath.replace("knu_it", "knu_cse");
            dir = new File(STATIC_RESOURCE_PATH + "/" + alternativePath);

            if (!dir.exists() || !dir.isDirectory()) {
                System.err.println("Directory not found: " + actualPath + " and " + STATIC_RESOURCE_PATH + "/" + alternativePath);
                return List.of();
            }
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return List.of();
        }

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
                        System.err.println("Failed to read image file: " + file.getName() + " - " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // 바이트 배열을 직접 반환하는 메서드
    public byte[] getFirstImageBytes(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return null;
        }

        String actualPath = STATIC_RESOURCE_PATH + "/" + imagePath;
        File dir = new File(actualPath);

        // 디렉토리가 존재하지 않는 경우 대안 경로 시도
        if (!dir.exists() || !dir.isDirectory()) {
            String alternativePath = imagePath.replace("knu_it", "knu_cse");
            dir = new File(STATIC_RESOURCE_PATH + "/" + alternativePath);

            if (!dir.exists() || !dir.isDirectory()) {
                return null;
            }
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }

        Optional<File> firstImage = Arrays.stream(files)
                .filter(file -> {
                    String name = file.getName().toLowerCase();
                    return SUPPORTED_EXTENSIONS.stream().anyMatch(name::endsWith);
                })
                .findFirst();

        if (firstImage.isEmpty()) {
            return null;
        }

        try {
            return Files.readAllBytes(firstImage.get().toPath());
        } catch (IOException e) {
            System.err.println("Failed to read image file: " + e.getMessage());
            return null;
        }
    }
}

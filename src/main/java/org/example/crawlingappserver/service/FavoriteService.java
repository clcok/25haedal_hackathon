package org.example.crawlingappserver.service;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.req.FavoritesWholeRequestDTO;
import org.example.crawlingappserver.dto.res.FavoriteWholeResponseDTO;
import org.example.crawlingappserver.entity.repository.ClubEventRepository;
import org.example.crawlingappserver.entity.repository.DepartmentEventRepository;
import org.example.crawlingappserver.entity.repository.OfficialEventRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");

    private final OfficialEventRepository officialEventRepository;
    private final DepartmentEventRepository departmentEventRepository;
    private final ClubEventRepository clubEventRepository;
    private final ImageService imageService;

    private String getFirstImageBase64(String imagePath) {
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

    public List<FavoriteWholeResponseDTO> getFavoritesInfo(List<FavoritesWholeRequestDTO> favorites) {
        return favorites.stream()
                .map(fav -> {
                    return switch (fav.domain()) {
                        case "official" -> officialEventRepository.findById(fav.id())
                                .map(e -> new FavoriteWholeResponseDTO(
                                        e.getId(),
                                        e.getTitle(),
                                        e.getCategory(),
                                        getFirstImageBase64(e.getImagePath())
                                ))
                                .orElse(null);
                        case "department" -> departmentEventRepository.findById(fav.id())
                                .map(e -> new FavoriteWholeResponseDTO(
                                        e.getId(),
                                        e.getTitle(),
                                        e.getCategory(),
                                        getFirstImageBase64(e.getImagePath())
                                ))
                                .orElse(null);
                        case "club" -> clubEventRepository.findById(fav.id())
                                .map(e -> new FavoriteWholeResponseDTO(
                                        e.getId(),
                                        e.getTitle(),
                                        e.getCategory(),
                                        getFirstImageBase64(e.getImagePath())
                                ))
                                .orElse(null);
                        default -> null;
                    };
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}

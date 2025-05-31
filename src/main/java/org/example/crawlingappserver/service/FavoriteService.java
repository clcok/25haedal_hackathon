package org.example.crawlingappserver.service;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.req.FavoritesWholeRequestDTO;
import org.example.crawlingappserver.dto.res.FavoriteWholeResponseDTO;
import org.example.crawlingappserver.entity.repository.ClubEventRepository;
import org.example.crawlingappserver.entity.repository.DepartmentEventRepository;
import org.example.crawlingappserver.entity.repository.OfficialEventRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");

    private final OfficialEventRepository officialEventRepository;
    private final DepartmentEventRepository departmentEventRepository;
    private final ClubEventRepository clubEventRepository;
    private final ImageService imageService;

    public List<FavoriteWholeResponseDTO> getFavoritesInfo(List<FavoritesWholeRequestDTO> favorites) {
        if (favorites == null || favorites.isEmpty()) {
            return List.of();
        }

        return favorites.stream()
                .map(fav -> {
                    if (fav == null || fav.domain() == null || fav.id() == null) {
                        return null;
                    }

                    return switch (fav.domain().toLowerCase()) {
                        case "official" -> officialEventRepository.findById(fav.id())
                                .map(e -> new FavoriteWholeResponseDTO(
                                        e.getId(),
                                        e.getTitle(),
                                        e.getCategory(),
                                        imageService.getFirstImageBase64(e.getImagePath())
                                ))
                                .orElse(null);
                        case "department" -> departmentEventRepository.findById(fav.id())
                                .map(e -> new FavoriteWholeResponseDTO(
                                        e.getId(),
                                        e.getTitle(),
                                        e.getCategory(),
                                        imageService.getFirstImageBase64(e.getImagePath())
                                ))
                                .orElse(null);
                        case "club" -> clubEventRepository.findById(fav.id())
                                .map(e -> new FavoriteWholeResponseDTO(
                                        e.getId(),
                                        e.getTitle(),
                                        e.getCategory(),
                                        imageService.getFirstImageBase64(e.getImagePath())
                                ))
                                .orElse(null);
                        default -> null;
                    };
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}

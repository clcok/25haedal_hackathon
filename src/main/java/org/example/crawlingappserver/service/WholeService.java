package org.example.crawlingappserver.service;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.res.EventWholeResponseDTO;
import org.example.crawlingappserver.entity.repository.ClubEventRepository;
import org.example.crawlingappserver.entity.repository.DepartmentEventRepository;
import org.example.crawlingappserver.entity.repository.OfficialEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WholeService {

    private final OfficialEventRepository officialEventRepository;
    private final DepartmentEventRepository departmentEventRepository;
    private final ClubEventRepository clubEventRepository;
    private final ImageService imageService;

    public List<EventWholeResponseDTO> getOfficialImages() {
        return officialEventRepository.findTop5ByDurationAfterOrderByDurationAsc(LocalDateTime.now())
                .stream()
                .map(e -> new EventWholeResponseDTO(
                        e.getId(),
                        e.getTitle(),
                        imageService.getFirstImageBase64(e.getImagePath())
                ))
                .collect(Collectors.toList());
    }

    public List<EventWholeResponseDTO> getDepartmentImages() {
        return departmentEventRepository.findTop5ByDurationAfterOrderByDurationAsc(LocalDateTime.now())
                .stream()
                .map(e -> new EventWholeResponseDTO(
                        e.getId(),
                        e.getTitle(),
                        imageService.getFirstImageBase64(e.getImagePath())
                ))
                .collect(Collectors.toList());
    }

    public List<EventWholeResponseDTO> getClubImages() {
        return clubEventRepository.findTop5ByDurationAfterOrderByDurationAsc(LocalDateTime.now())
                .stream()
                .map(e -> new EventWholeResponseDTO(
                        e.getId(),
                        e.getTitle(),
                        imageService.getFirstImageBase64(e.getImagePath())
                ))
                .collect(Collectors.toList());
    }

    public List<EventWholeResponseDTO> getEventsByCategoryAndType(String category, String requestType) {
        LocalDateTime now = LocalDateTime.now();

        return switch (requestType) {
            case "official" -> officialEventRepository.findAll().stream()
                    .filter(e -> category.equals(e.getCategory()) && e.getDuration().isAfter(now))
                    .sorted((e1, e2) -> e1.getDuration().compareTo(e2.getDuration()))
                    .limit(5)
                    .map(e -> new EventWholeResponseDTO(
                            e.getId(),
                            e.getTitle(),
                            imageService.getFirstImageBase64(e.getImagePath())
                    ))
                    .collect(Collectors.toList());
            case "department" -> departmentEventRepository.findAll().stream()
                    .filter(e -> category.equals(e.getCategory()) && e.getDuration().isAfter(now))
                    .sorted((e1, e2) -> e1.getDuration().compareTo(e2.getDuration()))
                    .limit(5)
                    .map(e -> new EventWholeResponseDTO(
                            e.getId(),
                            e.getTitle(),
                            imageService.getFirstImageBase64(e.getImagePath())
                    ))
                    .collect(Collectors.toList());
            case "club" -> clubEventRepository.findAll().stream()
                    .filter(e -> category.equals(e.getCategory()) && e.getDuration().isAfter(now))
                    .sorted((e1, e2) -> e1.getDuration().compareTo(e2.getDuration()))
                    .limit(5)
                    .map(e -> new EventWholeResponseDTO(
                            e.getId(),
                            e.getTitle(),
                            imageService.getFirstImageBase64(e.getImagePath())
                    ))
                    .collect(Collectors.toList());
            default -> List.of();
        };
    }

    public List<EventWholeResponseDTO> getDepartmentImagesByCollegeDepartmentCategory(
            String college, String department, String category) {
        LocalDateTime now = LocalDateTime.now();

        return departmentEventRepository.findAll().stream()
                .filter(e -> college.equals(e.getCollege()))
                .filter(e -> department.equals(e.getDepartment()))
                .filter(e -> category.equals(e.getCategory()))
                .filter(e -> e.getDuration().isAfter(now))
                .sorted((e1, e2) -> e1.getDuration().compareTo(e2.getDuration()))
                .limit(5)
                .map(e -> new EventWholeResponseDTO(
                        e.getId(),
                        e.getTitle(),
                        imageService.getFirstImageBase64(e.getImagePath())
                ))
                .collect(Collectors.toList());
    }
}

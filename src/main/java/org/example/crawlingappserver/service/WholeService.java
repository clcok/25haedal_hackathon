package org.example.crawlingappserver.service;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.res.EventWholeResponseDTO;
import org.example.crawlingappserver.entity.repository.ClubEventRepository;
import org.example.crawlingappserver.entity.repository.DepartmentEventRepository;
import org.example.crawlingappserver.entity.repository.OfficialEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WholeService {

    private final OfficialEventRepository officialEventRepository;
    private final DepartmentEventRepository departmentEventRepository;
    private final ClubEventRepository clubEventRepository;
    private final ImageService imageService;

    public List<EventWholeResponseDTO> getOfficialImages() {
        List<EventWholeResponseDTO> result = officialEventRepository
                .findTop5ByOrderByDurationAsc()
                .stream()
                .map(e -> new EventWholeResponseDTO(
                        e.getId(),
                        e.getTitle(),
                        imageService.getFirstImageBase64(e.getImagePath())
                ))
                .collect(Collectors.toList());

        return result;
    }

    public List<EventWholeResponseDTO> getDepartmentImages() {
        List<EventWholeResponseDTO> result = departmentEventRepository
                .findTop5ByOrderByDurationAsc()
                .stream()
                .map(e -> new EventWholeResponseDTO(
                        e.getId(),
                        e.getTitle(),
                        imageService.getFirstImageBase64(e.getImagePath())
                ))
                .collect(Collectors.toList());

        return result;
    }

    public List<EventWholeResponseDTO> getClubImages() {
        List<EventWholeResponseDTO> result = clubEventRepository
                .findTop5ByOrderByDurationAsc()
                .stream()
                .map(e -> new EventWholeResponseDTO(
                        e.getId(),
                        e.getTitle(),
                        imageService.getFirstImageBase64(e.getImagePath())
                ))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NoSuchElementException("공식 이벤트가 없습니다.");
        }
        return result;
    }

    public List<EventWholeResponseDTO> getEventsByCategoryAndType(String category, String requestType) {
        List<EventWholeResponseDTO> result = switch (requestType.toLowerCase()) {
            case "official" -> officialEventRepository.findAll().stream()
                    .filter(e -> category.equals(e.getCategory()))
                    .sorted((e1, e2) -> e1.getDuration().compareTo(e2.getDuration()))
                    .limit(5)
                    .map(e -> new EventWholeResponseDTO(
                            e.getId(),
                            e.getTitle(),
                            imageService.getFirstImageBase64(e.getImagePath())
                    ))
                    .collect(Collectors.toList());
            case "department" -> departmentEventRepository.findAll().stream()
                    .filter(e -> category.equals(e.getCategory()))
                    .sorted((e1, e2) -> e1.getDuration().compareTo(e2.getDuration()))
                    .limit(5)
                    .map(e -> new EventWholeResponseDTO(
                            e.getId(),
                            e.getTitle(),
                            imageService.getFirstImageBase64(e.getImagePath())
                    ))
                    .collect(Collectors.toList());
            case "club" -> clubEventRepository.findAll().stream()
                    .filter(e -> category.equals(e.getCategory()))
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

        if (result.isEmpty()) {
            throw new NoSuchElementException("해당 카테고리/타입의 이벤트가 없습니다.");
        }
        return result;
    }

    public List<EventWholeResponseDTO> getDepartmentImagesByCollegeDepartmentCategory(
            String college, String department, String category) {

        List<EventWholeResponseDTO> result = departmentEventRepository
                .findByCollegeAndDepartmentAndCategoryOrderByDurationAsc(
                        college, department, category)
                .stream()
                .limit(5)
                .map(e -> new EventWholeResponseDTO(
                        e.getId(),
                        e.getTitle(),
                        imageService.getFirstImageBase64(e.getImagePath())
                ))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new NoSuchElementException("해당 조건의 학과 이벤트가 없습니다.");
        }

        return result;
    }
}

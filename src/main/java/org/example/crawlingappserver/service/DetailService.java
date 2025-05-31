package org.example.crawlingappserver.service;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.res.EventDetailResponseDTO;
import org.example.crawlingappserver.entity.repository.ClubEventRepository;
import org.example.crawlingappserver.entity.repository.DepartmentEventRepository;
import org.example.crawlingappserver.entity.repository.OfficialEventRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DetailService {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private final OfficialEventRepository officialEventRepository;
    private final DepartmentEventRepository departmentEventRepository;
    private final ClubEventRepository clubEventRepository;
    private final ImageService imageService;

    public EventDetailResponseDTO getEventDetailByIdAndCategory(Long id, String category) {
        return switch (category.toLowerCase()) {
            case "official" -> officialEventRepository.findById(id)
                    .map(e -> new EventDetailResponseDTO(
                            e.getId(),
                            e.getTitle(),
                            imageService.getAllImagesBase64(e.getImagePath()),
                            e.getDuration() != null ? DATE_FORMATTER.format(e.getDuration()) : null,
                            e.getTarget(),
                            e.getBenefit(),
                            e.getNotice()
                    ))
                    .orElseThrow(() -> new NoSuchElementException("해당 id의 official 이벤트를 찾을 수 없습니다."));
            case "department" -> departmentEventRepository.findById(id)
                    .map(e -> new EventDetailResponseDTO(
                            e.getId(),
                            e.getTitle(),
                            imageService.getAllImagesBase64(e.getImagePath()),
                            e.getDuration() != null ? DATE_FORMATTER.format(e.getDuration()) : null,
                            e.getTarget(),
                            e.getBenefit(),
                            e.getNotice()
                    ))
                    .orElseThrow(() -> new NoSuchElementException("해당 id의 department 이벤트를 찾을 수 없습니다."));
            case "club" -> clubEventRepository.findById(id)
                    .map(e -> new EventDetailResponseDTO(
                            e.getId(),
                            e.getTitle(),
                            imageService.getAllImagesBase64(e.getImagePath()),
                            e.getDuration() != null ? DATE_FORMATTER.format(e.getDuration()) : null,
                            e.getTarget(),
                            e.getBenefit(),
                            e.getNotice()
                    ))
                    .orElseThrow(() -> new NoSuchElementException("해당 id의 club 이벤트를 찾을 수 없습니다."));
            default -> throw new IllegalArgumentException("지원하지 않는 category입니다: " + category);
        };
    }
}
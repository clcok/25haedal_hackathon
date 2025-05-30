package org.example.crawlingappserver.service;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.res.EventDetailResponseDTO;
import org.example.crawlingappserver.entity.domain.ClubEvent;
import org.example.crawlingappserver.entity.domain.DepartmentEvent;
import org.example.crawlingappserver.entity.domain.OfficialEvent;
import org.example.crawlingappserver.entity.repository.ClubEventRepository;
import org.example.crawlingappserver.entity.repository.DepartmentEventRepository;
import org.example.crawlingappserver.entity.repository.OfficialEventRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final OfficialEventRepository officialEventRepository;
    private final DepartmentEventRepository departmentEventRepository;
    private final ClubEventRepository clubEventRepository;
    private final ImageService imageService;


    public EventDetailResponseDTO getEventDetailById(Long id) {
        // 1. official_event에서 조회
        Optional<OfficialEvent> officialOpt = officialEventRepository.findById(id);
        if (officialOpt.isPresent()) {
            OfficialEvent e = officialOpt.get();
            return new EventDetailResponseDTO(
                    e.getTitle(),
                    imageService.getAllImagesBase64(e.getImagePath()),
                    e.getDuration().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    e.getTarget(),
                    e.getBenefit(),
                    e.getNotice()
            );
        }

        // 2. department_event에서 조회
        Optional<DepartmentEvent> departmentOpt = departmentEventRepository.findById(id);
        if (departmentOpt.isPresent()) {
            DepartmentEvent e = departmentOpt.get();
            return new EventDetailResponseDTO(
                    e.getTitle(),
                    imageService.getAllImagesBase64(e.getImagePath()),
                    e.getDuration().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    e.getTarget(),
                    e.getBenefit(),
                    e.getNotice()
            );
        }

        // 3. club_event에서 조회
        Optional<ClubEvent> clubOpt = clubEventRepository.findById(id);
        if (clubOpt.isPresent()) {
            ClubEvent e = clubOpt.get();
            return new EventDetailResponseDTO(
                    e.getTitle(),
                    imageService.getAllImagesBase64(e.getImagePath()),
                    e.getDuration().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    e.getTarget(),
                    e.getBenefit(),
                    e.getNotice()
            );
        }

        // 4. 없으면 예외
        throw new NoSuchElementException("해당 id의 이벤트를 찾을 수 없습니다.");
    }
}

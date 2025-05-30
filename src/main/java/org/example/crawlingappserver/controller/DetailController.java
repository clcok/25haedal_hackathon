package org.example.crawlingappserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.res.EventDetailResponseDTO;
import org.example.crawlingappserver.service.DetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;

    @GetMapping("/api/detail/{id}")
    public EventDetailResponseDTO getEventDetail(@PathVariable Long id) {
        return detailService.getEventDetailById(id);
    }
}

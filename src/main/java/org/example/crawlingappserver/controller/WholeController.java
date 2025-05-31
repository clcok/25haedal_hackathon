package org.example.crawlingappserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.crawlingappserver.dto.req.CategoryRequestDTO;
import org.example.crawlingappserver.dto.req.DepartmentRequestDTO;
import org.example.crawlingappserver.dto.res.EventWholeResponseDTO;
import org.example.crawlingappserver.service.WholeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class WholeController {
    private final WholeService wholeService;

    @GetMapping("/api/home")
    public Map<String, List<EventWholeResponseDTO>> getHomeImages() {
        Map<String, List<EventWholeResponseDTO>> response = new HashMap<>();
        response.put("official", wholeService.getOfficialImages());
        response.put("department", wholeService.getDepartmentImages());
        response.put("club", wholeService.getClubImages());
        return response;
    }

    @PostMapping("/api/home/category")
    public List<EventWholeResponseDTO> getCategoryEvents(@RequestBody CategoryRequestDTO requestDto) {
        return wholeService.getEventsByCategoryAndType(requestDto.category(), requestDto.request());
    }

    @PostMapping("/api/home/department")
    public List<EventWholeResponseDTO> getDepartmentEvents(@RequestBody DepartmentRequestDTO requestDto) {
        return wholeService.getDepartmentImagesByCollegeDepartmentCategory(
                requestDto.college(),
                requestDto.department(),
                requestDto.category()
        );
    }
}

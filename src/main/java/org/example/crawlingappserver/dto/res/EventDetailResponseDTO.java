package org.example.crawlingappserver.dto.res;

import java.util.List;

public record EventDetailResponseDTO(
        Long id,
        String title,
        List<String> images, // 폴더 내 모든 이미지의 Base64 인코딩 값
        String duration,
        String target,
        String benefit,
        String notice // 유의사항
) {
}

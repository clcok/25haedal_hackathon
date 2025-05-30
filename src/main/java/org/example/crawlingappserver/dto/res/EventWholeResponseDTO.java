package org.example.crawlingappserver.dto.res;

public record EventWholeResponseDTO(
        Long id,
        String title,
        String image //BASE64인코딩 이미지
) {
}
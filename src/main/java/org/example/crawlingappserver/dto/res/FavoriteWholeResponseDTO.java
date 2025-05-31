package org.example.crawlingappserver.dto.res;

public record FavoriteWholeResponseDTO(
        Long id,
        String title,
        String category,
        String image // Base64 인코딩된 이미지
) {
}

package org.example.crawlingappserver.dto.req;

public record FavoritesWholeRequestDTO(
        Long id,
        String domain // "club", "official", "department" 등
) {
}

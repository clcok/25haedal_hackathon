package org.example.crawlingappserver.dto.res;

import lombok.Builder;

@Builder
public record EventHomeResponse(
        Integer id,
        String title,
        String imagePath
) {
}

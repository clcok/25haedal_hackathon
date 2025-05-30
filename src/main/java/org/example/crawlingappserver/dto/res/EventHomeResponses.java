package org.example.crawlingappserver.dto.res;

import java.util.List;
import java.util.Map;

public record EventHomeResponses(Map<String, List<EventHomeResponse>> groupedEvents) {
}

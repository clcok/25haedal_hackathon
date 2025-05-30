package org.example.crawlingappserver.entity.repository;

import org.example.crawlingappserver.entity.domain.OfficialEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

// 공식계정 이벤트 Repository
public interface OfficialEventRepository extends JpaRepository<OfficialEvent, Long> {
    List<OfficialEvent> findTop5ByDurationAfterOrderByDurationAsc(LocalDateTime now);

    List<OfficialEvent> findTop5ByCategoryAndDurationAfterOrderByDurationAsc(String category, LocalDateTime now);
}
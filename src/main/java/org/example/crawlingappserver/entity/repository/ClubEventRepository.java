package org.example.crawlingappserver.entity.repository;

import org.example.crawlingappserver.entity.domain.ClubEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

// 동아리 이벤트 Repository
public interface ClubEventRepository extends JpaRepository<ClubEvent, Long> {
    List<ClubEvent> findTop5ByDurationAfterOrderByDurationAsc(LocalDateTime now);

    List<ClubEvent> findTop5ByCategoryAndDurationAfterOrderByDurationAsc(String category, LocalDateTime now);
}

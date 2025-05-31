package org.example.crawlingappserver.entity.repository;

import org.example.crawlingappserver.entity.domain.ClubEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

// 동아리 이벤트 Repository
public interface ClubEventRepository extends JpaRepository<ClubEvent, Long> {
    List<ClubEvent> findTop5ByDurationAfterOrderByDurationAsc(Date now);

    List<ClubEvent> findTop5ByCategoryAndDurationAfterOrderByDurationAsc(String category, Date now);

    List<ClubEvent> findTop5ByOrderByDurationAsc();
    
}

package org.example.crawlingappserver.entity.repository;

import org.example.crawlingappserver.entity.domain.OfficialEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

// 공식계정 이벤트 Repository
public interface OfficialEventRepository extends JpaRepository<OfficialEvent, Long> {
    List<OfficialEvent> findTop5ByDurationAfterOrderByDurationAsc(Date now);

    List<OfficialEvent> findTop5ByOrderByDurationDesc();

    List<OfficialEvent> findTop5ByCategoryAndDurationAfterOrderByDurationAsc(String category, Date now);

    List<OfficialEvent> findTop5ByOrderByDurationAsc();
}
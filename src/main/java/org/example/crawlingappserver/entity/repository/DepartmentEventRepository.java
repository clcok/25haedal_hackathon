package org.example.crawlingappserver.entity.repository;

import org.example.crawlingappserver.entity.domain.DepartmentEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

// 학과 관련 이벤트 Repository
public interface DepartmentEventRepository extends JpaRepository<DepartmentEvent, Long> {
    List<DepartmentEvent> findTop5ByDurationAfterOrderByDurationAsc(LocalDateTime now);

    List<DepartmentEvent> findTop5ByCategoryAndDurationAfterOrderByDurationAsc(String category, LocalDateTime now);
}

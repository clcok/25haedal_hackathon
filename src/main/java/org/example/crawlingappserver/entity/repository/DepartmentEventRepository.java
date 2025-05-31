package org.example.crawlingappserver.entity.repository;

import org.example.crawlingappserver.entity.domain.DepartmentEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

// 학과 관련 이벤트 Repository
public interface DepartmentEventRepository extends JpaRepository<DepartmentEvent, Long> {
    List<DepartmentEvent> findTop5ByDurationAfterOrderByDurationAsc(Date now);

    List<DepartmentEvent> findTop5ByCategoryAndDurationAfterOrderByDurationAsc(String category, Date now);

    List<DepartmentEvent> findByCollegeAndDepartmentAndCategoryAndDurationAfterOrderByDurationAsc(
            String college, String department, String category, Date now);
}

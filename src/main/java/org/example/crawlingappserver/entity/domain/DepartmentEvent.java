package org.example.crawlingappserver.entity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/* 학과 관련 이벤트 */
@Entity
@Table(name = "DepartmentEvent")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "benefit", nullable = false)
    private String benefit;

    @Column(name = "imagePath", nullable = false)
    private String imagePath;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "duration", nullable = false)
    private LocalDateTime duration;

    @Column(name = "college", nullable = false)
    private String college;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "notice", nullable = false)
    private String notice;
}

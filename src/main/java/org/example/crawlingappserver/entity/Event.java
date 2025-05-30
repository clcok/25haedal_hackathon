package org.example.crawlingappserver.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    /* --------------------------------- */
    /* ----------- Functions ----------- */
    /* --------------------------------- */
}

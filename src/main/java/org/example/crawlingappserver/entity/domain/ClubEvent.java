package org.example.crawlingappserver.entity.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/* 동아리 이벤트 */
@Entity
@Table(name = "ClubEvent")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postUrl", nullable = false)
    private String postUrl;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "target", nullable = false)
    private String target;
    
    @Column(name = "imagePath", nullable = false)
    private String imagePath;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "duration", nullable = false)
    private Date duration;

    @Column(name = "notice", nullable = true)
    private String notice;
}

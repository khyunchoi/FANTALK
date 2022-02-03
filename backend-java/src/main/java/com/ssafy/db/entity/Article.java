package com.ssafy.db.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 게시글 모델 정의
 */
@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private int hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @Builder
    public Article(String title, String content, int hits, User user, Community community) {
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.user = user;
        this.community = community;
    }

    // 조회수 증가를 위한 편의 함수
    public void raiseHits(int hits) {
        this.hits = hits;
    }

    // 제목 및 내용을 수정을 위한 편의 함수
    public void changeTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

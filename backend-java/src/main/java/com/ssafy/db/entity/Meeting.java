package com.ssafy.db.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 팬미팅 모델 정의
 */
@Entity
@Getter
@NoArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private boolean isActive;

    @NotNull
    private boolean isInManager;

    @NotNull
    private int maxUser;

    @NotNull
    private LocalDateTime openDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Meeting(String title, boolean isActive, boolean isInManager, int maxUser, LocalDateTime openDate, User user) {
        this.title = title;
        this.isActive = isActive;
        this.isInManager = isInManager;
        this.maxUser = maxUser;
        this.openDate = openDate;
        this.user = user;
    }

    // 제목, 오픈일자, 제한인원 수정을 위한 편의 함수
    public void changeTitleAndOpenDateAndMaxUser(String title, LocalDateTime openDate, int maxUser) {
        this.title = title;
        this.openDate = openDate;
        this.maxUser = maxUser;
    }

    // 팬미팅 진행 여부 변경을 위한 편의 함수
    public void changeIsActive() {
        if (this.isActive) {
            this.isActive = false;
        } else {
            this.isActive = true;
        }
    }
}

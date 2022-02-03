package com.ssafy.db.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Community {//extends BaseEntityAll{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @NotNull
    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(name = "LOGO_IMAGE", nullable = false)
    private String logoImage;

    @Column(name = "BACK_GROUND_IMAGE", nullable = false)
    private String backgroundImage;

    @Builder
    public Community(String name, String title, String logoImage, String backgroundImage) {
        this.name = name;
        this.title = title;
        this.logoImage = logoImage;
        this.backgroundImage = backgroundImage;
    }
}

package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Community extends BaseEntityAll{

    @Column(nullable = false)
    private String title;

    @Column(name = "LOGO_IMAGE", nullable = false)
    private String logoImage;

    @Column(name = "BACK_GROUND_IMAGE", nullable = false)
    private String backgroundImage;

    @Builder
    public Community(String title, String logoImage, String backgroundImage) {
        this.title = title;
        this.logoImage = logoImage;
        this.backgroundImage = backgroundImage;
    }
}

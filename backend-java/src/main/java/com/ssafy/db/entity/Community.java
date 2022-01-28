package com.ssafy.db.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Community extends BaseEntityAll{

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

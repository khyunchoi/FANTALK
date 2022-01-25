package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String profileImage;

    @ColumnDefault("false")
    private boolean isManager;

    @ColumnDefault("false")
    private boolean isDelete;

    @Builder
    public User(String name, String email, String profileImage){
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    public User update(String name, String profileImage, boolean isManager, boolean isDelete){
        this.name = name;
        this.profileImage = profileImage;
        this.isManager = isManager;
        this.isDelete = isDelete;
        return this;
    }
}

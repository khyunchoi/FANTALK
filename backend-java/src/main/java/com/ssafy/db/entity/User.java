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
    private String username;

    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    private String profileImage;

    private String role;

    @ColumnDefault("false")
    private boolean isManager;

    @ColumnDefault("false")
    private boolean isDelete;

    @Builder
    public User(String username, String email, String profileImage){
        this.username = username;
        this.email = email;
        this.profileImage = profileImage;
    }

    public User update(String username, String profileImage, boolean isManager, boolean isDelete){
        this.username = username;
        this.profileImage = profileImage;
        this.isManager = isManager;
        this.isDelete = isDelete;
        return this;
    }
}

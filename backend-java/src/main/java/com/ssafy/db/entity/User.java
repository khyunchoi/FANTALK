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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String password;

    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String profileImage;

    private String role; // ROLE_USER, ROLE_MANAGER

    @ColumnDefault("false")
    private boolean isDelete;

    @Builder
    public User(String username, String name, String email, String profileImage, String role){
        this.username = username;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.role = role;
    }

    public User update(String username, String profileImage, boolean isDelete){
        this.username = username;
        this.profileImage = profileImage;
        this.isDelete = isDelete;
        return this;
    }
}

package com.ssafy.db.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 커뮤니티 로고 이미지 정의
 */
@Entity
@Getter
@NoArgsConstructor
public class CommunityImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "original_file_name")
    private String originalFileName;

    @NotNull
    @Column(name = "stored_file_path")
    private String storedFilePath;

    @Column(name = "file_size")
    private long fileSize;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @Builder
    public CommunityImage(String originalFileName, String storedFilePath, Long fileSize, Community community) {
        this.originalFileName = originalFileName;
        this.storedFilePath = storedFilePath;
        this.fileSize = fileSize;
        this.community = community;
    }
}
package com.ssafy.api.request;


import com.ssafy.db.entity.Community;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 커뮤니티 이미지 등록 DTO 정의
 */
public class CommunityImageRegisterPostReq {

    @ApiModelProperty(name="원본 파일이름", example="파일1")
    private String originalFileName;

    @ApiModelProperty(name="파일 저장경로", example="저장경로1")
    private String storedFilePath;

    @ApiModelProperty(name="파일 사이즈", example="1xxxx")
    private long fileSize;
}

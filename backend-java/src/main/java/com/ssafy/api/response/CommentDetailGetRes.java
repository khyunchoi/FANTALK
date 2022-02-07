package com.ssafy.api.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * 댓글 상세 반환 DTO 정의
 */
@Getter
@Setter
@ApiModel("CommentDetailGetResponse")
public class CommentDetailGetRes {

    @ApiModelProperty(name="댓글 ID", example="1")
    Long commentId;

    @ApiModelProperty(name="댓글 내용", example="내용1")
    String content;

    @ApiModelProperty(name="등록일자", example="2022-01-01 00:00")
    LocalDateTime createdAt;

    @ApiModelProperty(name="작성자 이메일", example="xxxxxx@xxx.com")
    String email;
}
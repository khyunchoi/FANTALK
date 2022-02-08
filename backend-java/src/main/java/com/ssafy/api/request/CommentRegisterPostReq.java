package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * 댓글 등록 DTO 정의
 */
@Getter
@Setter
@ApiModel("CommentRegisterPostRequest")
public class CommentRegisterPostReq {

    @ApiModelProperty(name="댓글 내용", example="내용1")
    private String content;
}

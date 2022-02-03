package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * 댓글 삭제 DTO 정의
 */
@Getter
@Setter
@ApiModel("CommentDeleteRequest")
public class CommentDeleteReq {

    @ApiModelProperty(name="회원 id", example="1")
    private Long userId;
}

package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시글 삭제 DTO 정의
 */
@Getter
@Setter
@ApiModel("ArticleDeleteRequest")
public class ArticleDeleteReq {

    @ApiModelProperty(name="회원 id", example="1")
    Long userId;
}

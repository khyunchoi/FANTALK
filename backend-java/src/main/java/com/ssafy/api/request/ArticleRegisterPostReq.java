package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * 커뮤니티생성 ([POST] /api/v1/auth/{title}/articles) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ArticleRegisterPostRequest")
public class ArticleRegisterPostReq {

    @ApiModelProperty(name="글 제목", example="제목123")
    String title;

    @ApiModelProperty(name="글 내용", example="jpa 어려워용")
    String content;

}

package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 커뮤니티생성 ([POST] /api/v1/auth/communities) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommunityRegisterPostRequest")
public class CommunityRegisterPostReq {
    @ApiModelProperty(name="연예인 이름", example="아이유")
    String name;
    @ApiModelProperty(name="팬 커뮤니티 이름", example="어른말고아이유")
    String title;

    @ApiModelProperty(name="로고이미지", example="limages")
    String logoImage;

    @ApiModelProperty(name="배경화면", example="bimages")
    String backgroundImage;
}

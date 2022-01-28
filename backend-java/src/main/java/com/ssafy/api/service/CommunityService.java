package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;

import java.util.List;

public interface CommunityService {
    void registerCommunity(CommunityRegisterPostReq communityInfo);
    List<Community> getAllCommunity();
    List<Community> findCommunityByTitle(String title);
    boolean findCommunityByName(String name);
}

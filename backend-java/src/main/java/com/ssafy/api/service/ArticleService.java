package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles(Long community_id);
    void registerArticle(ArticleRegisterPostReq articleInfo, Community community);
}

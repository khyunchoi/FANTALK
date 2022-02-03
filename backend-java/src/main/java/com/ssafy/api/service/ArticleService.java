package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<Article> getAllArticles(Long community_id);
    void registerArticle(ArticleRegisterPostReq articleInfo, Community community);
    Optional<Article> getArticle(Long community_id, Long article_id);
    Optional<Article> modifyArticle(ArticleRegisterPostReq articleInfo, Community community, Long article_id);
    public List<Article> searchArticle(Long community_id, String category, String search);
}

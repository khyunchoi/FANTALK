package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.api.response.ArticleDetailGetRes;
import com.ssafy.api.response.ArticleListGetRes;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;

import java.util.List;
import java.util.Optional;


/**
 * 게시글 서비스 인터페이스 정의
 */
public interface ArticleService {
    void registerArticle(ArticleRegisterPostReq articleInfo, Community community);
    List<ArticleListGetRes> getAllArticles(Long communityId);
    ArticleDetailGetRes getArticleDetail(Long communityId, Long articleId);
    Optional<Article> modifyArticle(ArticleRegisterPostReq articleInfo, Community community, Long article_id);
    public List<Article> searchArticle(Long community_id, String category, String search);
}

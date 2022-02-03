package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.api.response.ArticleDetailGetRes;
import com.ssafy.api.response.ArticleListGetRes;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 게시글 서비스 구현체 정의
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;
    private final UserService userService;

    // 게시글 등록
    @Override
    public void registerArticle(ArticleRegisterPostReq articleInfo, Community community) {

        try {
            User user = userService.findById(articleInfo.getUserId());
            Article article = Article.builder()
                    .user(user)
                    .community(community)
                    .title(articleInfo.getTitle())
                    .content(articleInfo.getContent())
                    .hits(0)
                    .build();
            articleRepository.save(article);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 팬 커뮤니티 안의 모든 게시글 조회
    @Override
    public List<ArticleListGetRes> getAllArticles(Long communityId) {
        List<Article> articles = articleRepository.findAllByCommunityId(communityId);
        List<ArticleListGetRes> articleListGetRes = new ArrayList<>();

        for (Article article : articles) {
            ArticleListGetRes articleRes = new ArticleListGetRes();
            articleRes.setArticleId(article.getId());
            articleRes.setTitle(article.getTitle());
            articleRes.setCreatedAt(article.getCreatedAt());
            articleRes.setHits(article.getHits());
            articleRes.setEmail(article.getUser().getEmail());
            articleListGetRes.add(articleRes);
        }

        return articleListGetRes;
    }

    // 게시글 상세 조회
    @Override
    public ArticleDetailGetRes getArticleDetail(Long articleId, Long communityId) {
        Article article = articleRepository.findByIdAndCommunityId(articleId, communityId).get();
        // 조회수 1 증가
        article.setHits(article.getHits() + 1);
        articleRepository.save(article);

        ArticleDetailGetRes articleDetailGetRes = new ArticleDetailGetRes();
        articleDetailGetRes.setArticleId(article.getId());
        articleDetailGetRes.setTitle(article.getTitle());
        articleDetailGetRes.setContent(article.getContent());
        articleDetailGetRes.setCreatedAt(article.getCreatedAt());
        articleDetailGetRes.setHits(article.getHits());
        articleDetailGetRes.setEmail(article.getUser().getEmail());
        return articleDetailGetRes;
    }

    //게시글 수정
    @Override
    public Optional<Article> modifyArticle(ArticleRegisterPostReq articleInfo, Community community, Long article_id) {
        Optional<Article> article = this.articleRepository.findByIdAndCommunityId(article_id, community.getId());

        if(article.isPresent()){
            Article t = Article.builder()
                    .title(articleInfo.getTitle())
                    .content(articleInfo.getContent())
                    .community(community)
                .build();
            this.articleRepository.save(t);
        }
        return article;
    }

    //게시글 검색
    public List<Article> searchArticle(Long community_id, String category, String search){
        List<Article> articles = new ArrayList<>();
        if(category.equals("title")){
            return articleRepository.findAllByCommunity_IdAndTitleContaining(community_id,search);
        }else if(category.equals("content")){
            return articleRepository.findAllByCommunity_IdAndContentContaining(community_id,search);
        }

        return articles;
    }
}

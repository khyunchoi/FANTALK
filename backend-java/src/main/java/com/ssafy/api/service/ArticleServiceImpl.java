package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;
import com.ssafy.db.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
@RequiredArgsConstructor// final과 함께 쓰여 생성자 주입을 해준다
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;


    @Override
    public List<Article> getAllArticles(Long community_id) {
        return articleRepository.findAllByCommunity_Id(community_id);
    }

    // 게시글 작성
    @Override
    public void registerArticle(ArticleRegisterPostReq articleInfo, Community community) {

        try{
            Article article = Article.builder()
                    .community(community)
                    .title(articleInfo.getTitle())
                    .content(articleInfo.getContent())
                    .build();
            articleRepository.save(article);
            // IllegalArgumentException – in case the given entity is null
            return;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}

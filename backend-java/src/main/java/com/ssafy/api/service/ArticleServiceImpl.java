package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;
import com.ssafy.db.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //게시글 조회
    @Override
    public Optional<Article> getArticle(Long community_id, Long article_id) {
        return articleRepository.findByIdAndCommunity_Id(article_id, community_id);
    }

    //게시글 수정
    @Override
    public Optional<Article> modifyArticle(ArticleRegisterPostReq articleInfo, Community community, Long article_id) {
        Optional<Article> article = this.articleRepository.findByIdAndCommunity_Id(article_id, community.getId());

        if(article.isPresent()){
            Article t = Article.builder()
                    .id(article.get().getId())
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

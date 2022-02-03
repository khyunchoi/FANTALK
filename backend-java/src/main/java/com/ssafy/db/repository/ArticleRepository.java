package com.ssafy.db.repository;

import com.ssafy.api.service.ArticleService;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByCommunity_Id(Long community_id);
    Optional<Article> findByIdAndCommunity_Id(Long id, Long community_id);

//    @Query("select a from Article a where a.title like %:title%")
//    List<Article> searchArticles(@Param("title") String title);
    List<Article> findAllByCommunity_IdAndTitleContaining(Long community_id,String search);//title
    List<Article> findAllByCommunity_IdAndContentContaining(Long community_id,String search);
}

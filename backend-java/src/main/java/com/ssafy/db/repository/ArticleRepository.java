package com.ssafy.db.repository;

import com.ssafy.db.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * 게시글 레포지토리 정의
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByCommunityId(Long communityId);
    Optional<Article> findByIdAndCommunityId(Long id, Long communityId);
    List<Article> findAllByCommunity_IdAndTitleContaining(Long community_id,String search);
    List<Article> findAllByCommunity_IdAndContentContaining(Long community_id,String search);
}

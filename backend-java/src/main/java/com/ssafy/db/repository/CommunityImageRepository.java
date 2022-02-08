package com.ssafy.db.repository;

import com.ssafy.db.entity.CommunityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityImageRepository extends JpaRepository<CommunityImage, Long> {

    Optional<CommunityImage> findByCommunityId(Long communityId);
}

package com.ssafy.api.service;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;
import com.ssafy.db.repository.ArticleRepository;
import com.ssafy.db.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("communityService")
@RequiredArgsConstructor// final과 함께 쓰여 생성자 주입을 해준다
public class CommunityServiceImpl implements CommunityService{

    private final CommunityRepository communityRepository;


    // 팬 커뮤니티 생성
    @Override
    public void registerCommunity(CommunityRegisterPostReq communityInfo){
        // 같은 title이 존재하는지 확인하는 것,
        try{
            Community community = Community.builder()
                    .name(communityInfo.getName())
                    .title(communityInfo.getTitle())
                    .logoImage(communityInfo.getLogoImage())
                    .backgroundImage(communityInfo.getBackgroundImage())
                    .build();
            communityRepository.save(community);
            return;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    //모든 팬 커뮤니티 조회
    @Override
    public List<Community> getAllCommunity() {
        List<Community> communities = communityRepository.findAll();
        return communities;
    }

    //title에 해당하는 커뮤니티 조회
    @Override
    public List<Community> findCommunityByTitle(String title) {
        //Community community = communityRepository.findByTitle(title); optional 사용안헐시, 없으면 에러페이지라 이거로 사용?
        List<Community> communities = communityRepository.findByTitleContaining(title);
        return communities;
    }

    @Override
    public boolean findCommunityByName(String name) {
        if(communityRepository.findByName(name).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Community findById(Long id) {
        try{
            Community community = communityRepository.findById(id).get();
            //return non-null value
            //throws NoSuchElementException – if no value is present
            return community;
        }catch(Exception e){
            throw e;
        }
    }


}

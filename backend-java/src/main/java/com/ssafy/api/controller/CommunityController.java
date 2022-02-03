package com.ssafy.api.controller;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.api.service.ArticleService;
import com.ssafy.api.service.CommunityService;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.util.List;
import java.util.Optional;


/**
 * 팬 커뮤니티 컨트롤러 정의
 */
@Api(value = "커뮤니티 API", tags = {"Community"})
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/communities")
public class CommunityController {

    private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    private CommunityService communityService;

    @Autowired
    private ArticleService articleService;

    // 팬 커뮤니티 생성
    @PostMapping
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공(SUCCESS)"),
            @ApiResponse(code = 204, message = "실패(FAIL)"),
            @ApiResponse(code = 400, message = "이미 존재하는 팬 커뮤니티(ALREADY EXIST)"),
    })
    @ApiOperation(value = "팬 커뮤니티 등록", notes = "팬 커뮤니티를 등록")
    public ResponseEntity<String> registerCommunity(
            @RequestBody @ApiParam(value="커뮤니티 정보", required = true) CommunityRegisterPostReq communityInfo) {
        logger.info("registerCommunity 호출");

        // 같은 name이 존재할시에 ALREADY EXIST 반환
        if (communityService.findByName(communityInfo.getName())) {
            return new ResponseEntity<String>("ALREADY EXIST", HttpStatus.BAD_REQUEST);
        }

        try {
            communityService.registerCommunity(communityInfo);
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
        }
    }

    // 모든 팬 커뮤니티 조회
    @GetMapping
    @ApiResponses({
            @ApiResponse(code = 200, message = "팬 커뮤니티 목록 리스트 반환, 없을 시 [] 반환"),
    })
    @ApiOperation(value = "모든 팬 커뮤니티 목록 조회", notes = "모든 팬 커뮤니티 목록을 조회")
    public ResponseEntity<List<Community>> getAllCommunity() {
        logger.info("getAllCommunity 호출");

        return new ResponseEntity<List<Community>>(communityService.getAllCommunity(), HttpStatus.OK);
    }

    // 팬 커뮤니티 이름을 기반으로 검색
    @GetMapping("/search")
    @ApiResponses({
            @ApiResponse(code = 200, message = "리스트 반환, 없을 시 [] 반환"),
    })
    @ApiOperation(value = "팬 커뮤니티 검색", notes = "팬 커뮤니티 이름을 기반으로 검색")
    public ResponseEntity<List<Community>> searchCommunity(@RequestParam("q") @ApiParam(value="검색어", required = true) String searchWord) {
        logger.info("searchCommunity 호출");

        return new ResponseEntity<List<Community>>(communityService.searchCommunity(searchWord), HttpStatus.OK);
    }

// --------------------------------------article -----------------------------------------------


    //게시글 수정
    @PutMapping("/{community_id}/articles/{article_id}")
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")//, response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 204, message = "오류"),
    })
    public ResponseEntity<?> modifyArticle(
            @PathVariable("community_id") @ApiParam(value="커뮤니티 id", required = true) Long community_id,
            @PathVariable("article_id") @ApiParam(value="게시글 번호", required = true) Long article_id,
            @RequestBody @ApiParam(value="글 정보", required = true) ArticleRegisterPostReq articleInfo) {
        logger.info("modifyArticle 호출");
        try {
            Community community = communityService.findById(community_id);
            Optional<Article> article = this.articleService.modifyArticle(articleInfo, community, article_id);
            if(article.isPresent()){
                return ResponseEntity.ok(article);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();// 수정
        }
        return ResponseEntity.badRequest().build();
    }
}

package com.ssafy.api.controller;

import com.ssafy.api.request.ArticleRegisterPostReq;
import com.ssafy.api.response.ArticleDetailGetRes;
import com.ssafy.api.response.ArticleListGetRes;
import com.ssafy.api.service.ArticleService;
import com.ssafy.api.service.CommunityService;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Community;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * 게시글 컨트롤러 정의
 */
@Api(value = "게시글 API", tags = {"Article"})
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/communities/{community_id}")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    private CommunityService communityService;

    @Autowired
    private ArticleService articleService;

    // 게시글 등록
    @PostMapping("/articles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공(SUCCESS)"),
            @ApiResponse(code = 204, message = "게시글 작성 오류(FAIL)"),
            @ApiResponse(code = 400, message = "커뮤니티 탐색 오류(NO COMMUNITY)"),
    })
    @ApiOperation(value = "게시글 등록", notes = "새로운 게시글을 등록")
    public ResponseEntity<String> registerArticle(
            @RequestBody @ApiParam(value="게시글 정보", required = true) ArticleRegisterPostReq articleInfo,
            @PathVariable("community_id") @ApiParam(value="커뮤니티 id", required = true) Long communityId) {
        logger.info("registerArticle 호출");

        try {
            Community community = communityService.findById(communityId);
            articleService.registerArticle(articleInfo,community);//
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

        } catch (NoSuchElementException e){
            return new ResponseEntity<String>("NO COMMUNITY", HttpStatus.BAD_REQUEST);

        } catch (IllegalArgumentException e){
            return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
        }
    }

    // 커뮤니티 안의 모든 게시글 목록 조회
    @GetMapping("/articles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "모든 게시글 리스트 반환, 없을 시 [] 반환"),
    })
    @ApiOperation(value = "커뮤니티 안의 모든 게시글 목록 조회", notes = "커뮤니티 안의 모든 게시글 목록을 조회")
    public ResponseEntity<List<ArticleListGetRes>> getAllArticles(
            @PathVariable("community_id") @ApiParam(value="커뮤니티 id", required = true) Long communityId) {
        logger.info("getAllArticles 호출");

        return new ResponseEntity<List<ArticleListGetRes>>(articleService.getAllArticles(communityId), HttpStatus.OK);
    }

    // 게시글 상세 조회
    @GetMapping("/articles/{article_id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "커뮤니티 탐색 오류"),
    })
    @ApiOperation(value = "게시글 상세 조회", notes = "게시글을 상세 조회")
    public ResponseEntity<ArticleDetailGetRes> getArticleDetail(
            @PathVariable("community_id") @ApiParam(value="커뮤니티 id", required = true) Long communityId,
            @PathVariable("article_id") @ApiParam(value="게시글 id", required = true) Long articleId) {
        logger.info("getArticle 호출");

        try {
            return new ResponseEntity<ArticleDetailGetRes>(articleService.getArticleDetail(articleId, communityId), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<ArticleDetailGetRes>(HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 검색
    @GetMapping("/{community_id}/articles/search")
    @ApiOperation(value = "게시글 조회(분류,검색어)", notes = "게시글을 조회한다.")
    public ResponseEntity<?> findArticle(
            @PathVariable("community_id") @ApiParam(value="커뮤니티 id", required = true) Long community_id,
            @RequestParam("category") @ApiParam(value="셀렉트 박스", required = true) String category,
            @RequestParam("search") @ApiParam(value="검색어", required = true) String search){
        logger.info("searchArticle 호출");

        return new ResponseEntity<List<Article>>(articleService.searchArticle(community_id,category,search), HttpStatus.OK);
    }
}

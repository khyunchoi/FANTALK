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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Api(value = "게시판 API", tags = {"Community"})
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/communities")
public class CommunityController {

    private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private CommunityService communityService;

    @Autowired
    private ArticleService articleService;

    // 팬 커뮤니티 생성
//    @PostMapping()
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "이미 존재하는 연예인 커뮤니티"),
            @ApiResponse(code = 204, message = "실패")
    })
    public ResponseEntity<String> registerCommunity(
            @RequestBody @ApiParam(value="커뮤니티 정보", required = true) CommunityRegisterPostReq communityInfo) {
        logger.info("registerCommunity 호출");

        // 같은 name이 존재할시에 true 반환
        if(communityService.findCommunityByName(communityInfo.getName())){
            return new ResponseEntity<String>("ALREADY EXIST", HttpStatus.BAD_REQUEST);
        }

        try{
            communityService.registerCommunity(communityInfo);
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);// 200 ** post, put 요청 성공시 보통 201이라고 함
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);// 500과 구분용으로
        }
    }

    //모든 팬 커뮤니티 조회
    @GetMapping
    @ApiResponses({
            @ApiResponse(code = 200, message = "리스트 반환, 없을 시 [] 반환"),
    })
    @ApiOperation(value = "모든 팬 커뮤니티 목록 조회", notes = "모든 커뮤니티를 조회한다.")//, response = String.class)
    public ResponseEntity<List<Community>> getAllCommunity() {
        logger.info("listCommunity 호출");

        return new ResponseEntity<List<Community>>(communityService.getAllCommunity(), HttpStatus.OK);
    }

    //title로 팬 커뮤니티 조회
    //검색기능인지 확인
    @GetMapping("/search")
    @ApiResponses({
            @ApiResponse(code = 200, message = "리스트 반환, 없을 시 [] 반환"),
    })
    @ApiOperation(value = "title로 팬 커뮤니티 검색", notes = "특정 커뮤니티를 조회한다.")//, response = String.class)
    public ResponseEntity<List<Community>> searchCommunity(@RequestParam("title") @ApiParam(value="커뮤니티 이름", required = true) String title) {
        logger.info("listCommunity 호출");

        return new ResponseEntity<List<Community>>(communityService.searchCommunity(title), HttpStatus.OK);
        // 검색결과가 없을 때 빈칸
    }

// --------------------------------------article -----------------------------------------------

    //모든 게시판 글 조회(title)
    @GetMapping("/{community_id}/articles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "모든 게시글 리스트 반환, 없을 시 [] 반환"),
    })
    @ApiOperation(value = "모든 게시판 글 조회(특정 게시판)", notes = "모든 게시판 글을 조회한다.")//, response = String.class)
    public ResponseEntity<List<Article>> getAllArticles(
            @PathVariable("community_id") @ApiParam(value="커뮤니티 ", required = true) Long community_id) {
        logger.info("getAllArticle 호출");

        return new ResponseEntity<List<Article>>(articleService.getAllArticles(community_id), HttpStatus.OK);
    }

    //게시글 작성
    @PostMapping("/{community_id}/articles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "커뮤니티 탐색 오류"),
            @ApiResponse(code = 204, message = "게시글 작성 오류"),
    })
    @ApiOperation(value = "게시글 작성", notes = "새로운 게시글을 생성한다.")//, response = String.class)
    public ResponseEntity<String> registerArticle(
            @RequestBody @ApiParam(value="글 정보", required = true) ArticleRegisterPostReq articleInfo,
            @PathVariable("community_id") @ApiParam(value="커뮤니티 ", required = true) Long community_id) {
        logger.info("registerCommunity 호출");
//
        try{
            Community community = communityService.findById(community_id);//NoSuchElementException
            articleService.registerArticle(articleInfo,community);//
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

        }catch(NoSuchElementException e){// community 탐색 실패
            return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);

        }catch(IllegalArgumentException e){// 게시글 작성 실패
            return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
        }
    }

    //게시글 상세 조회
    @GetMapping("/{community_id}/articles/{article_id}")
    @ApiOperation(value = "게시글 조회", notes = "게시글을 조회한다.")//, response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 204, message = "커뮤니티 탐색 오류"),
    })
    public ResponseEntity<?> getArticle(
            @PathVariable("community_id") @ApiParam(value="커뮤니티 id", required = true) Long community_id,
            @PathVariable("article_id") @ApiParam(value="게시글 번호", required = true) Long article_id) {
        logger.info("getArticle 호출");

        Optional<Article> article = articleService.getArticle(community_id,article_id);
        if(article.isPresent()){
            return ResponseEntity.ok(article);
        }
        return ResponseEntity.badRequest().build();
    }

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

    //게시글 검색
    @GetMapping("/{community_id}/articles/search")
    @ApiOperation(value = "게시글 조회(분류,검색어)", notes = "게시글을 조회한다.")//, response = String.class)
    public ResponseEntity<?> findArticle(
            @PathVariable("community_id") @ApiParam(value="커뮤니티 id", required = true) Long community_id,
            @RequestParam("category") @ApiParam(value="셀렉트 박스", required = true) String category,// 제목 , 내용
            @RequestParam("search") @ApiParam(value="검색어", required = true) String search){
        logger.info("searchArticle 호출");

        return new ResponseEntity<List<Article>>(articleService.searchArticle(community_id,category,search), HttpStatus.OK);
    }
}

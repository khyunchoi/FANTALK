package com.ssafy.api.controller;

import com.ssafy.api.request.MeetingRegisterPostReq;
import com.ssafy.api.response.MeetingDetailGetRes;
import com.ssafy.api.service.MeetingService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.Meeting;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.MeetingRepository;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 팬미팅 컨트롤러 정의
 */
@Api(value = "팬미팅 API", tags = {"Meeting"})
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/v1/meetings")
public class MeetingController {

    private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private UserService userService;

    @Autowired
    private MeetingRepository meetingRepository;

    // 팬미팅 생성
    @PostMapping
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공(SUCCESS)"),
            @ApiResponse(code = 204, message = "실패(FAIL)"),
            @ApiResponse(code = 400, message = "관리자 인증 실패(NO MANAGER)"),
    })
    @ApiOperation(value = "팬미팅 등록", notes = "팬미팅을 등록")
    public ResponseEntity<String> registerMeeting(
            @RequestBody @ApiParam(value="팬미팅 정보", required = true) MeetingRegisterPostReq meetingInfo) {
        logger.info("registerMeeting 호출");

        SsafyUserDetails userDetails = (SsafyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());

        // 관리자 여부 확인
        if (user.getRole().equals("ROLE_USER")) {
            try {
                meetingService.registerMeeting(meetingInfo, user);
                return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<String>("NO MANAGER", HttpStatus.BAD_REQUEST);
        }
    }

    // 모든 팬미팅 조회
    @GetMapping
    @ApiResponses({
            @ApiResponse(code = 200, message = "팬미팅 목록 리스트 반환, 없을 시 [] 반환"),
    })
    @ApiOperation(value = "모든 팬미팅 목록 조회", notes = "모든 팬미팅 목록을 조회")
    public ResponseEntity<List<MeetingDetailGetRes>> getAllMeeting() {
        logger.info("getAllMeeting 호출");

        return new ResponseEntity<List<MeetingDetailGetRes>>(meetingService.getAllMeeting(), HttpStatus.OK);
    }

    // 팬미팅 제목을 기반으로 검색
    @GetMapping("/search")
    @ApiResponses({
            @ApiResponse(code = 200, message = "리스트 반환, 없을 시 [] 반환"),
    })
    @ApiOperation(value = "팬미팅 검색", notes = "팬미팅 제목을 기반으로 검색")
    public ResponseEntity<List<MeetingDetailGetRes>> searchMeeting(@RequestParam("q") @ApiParam(value="검색어", required = true) String searchWord) {
        logger.info("searchMeeting 호출");

        return new ResponseEntity<List<MeetingDetailGetRes>>(meetingService.searchMeeting(searchWord), HttpStatus.OK);
    }

    // 팬미팅 수정
    @PutMapping("/{meetingId}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공(SUCCESS)"),
            @ApiResponse(code = 204, message = "실패(FAIL)"),
            @ApiResponse(code = 400, message = "관리자 인증 실패(NO MANAGER)"),
    })
    @ApiOperation(value = "팬미팅 수정", notes = "팬미팅을 수정")
    public ResponseEntity<String> modifyMeeting(
            @PathVariable("meetingId") @ApiParam(value="팬미팅 id", required = true) Long meetingId,
            @RequestBody @ApiParam(value="팬미팅 정보", required = true) MeetingRegisterPostReq meetingInfo) {
        logger.info("modifyMeeting 호출");

        SsafyUserDetails userDetails = (SsafyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());

        // 관리자 여부 확인
        if (user.getRole().equals("ROLE_USER")) {
            try {
                Meeting meeting = meetingRepository.findById(meetingId).get();
                // 현재 로그인된 회원 id와 수정 요청한 팬미팅의 회원 id가 일치하는지 확인
                if (meeting.getUser().getId().equals(user.getId())) {
                    meetingService.modifyMeeting(meetingInfo, meetingId);
                    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("NO MANAGER", HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e){
                return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<String>("NO MANAGER", HttpStatus.BAD_REQUEST);
        }
    }

    // 팬미팅 삭제
    @DeleteMapping("/{meetingId}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공(SUCCESS)"),
            @ApiResponse(code = 204, message = "실패(FAIL)"),
            @ApiResponse(code = 400, message = "관리자 인증 실패(NO MANAGER)"),
    })
    @ApiOperation(value = "팬미팅 삭제", notes = "팬미팅을 삭제")
    public ResponseEntity<String> deleteMeeting(
            @PathVariable("meetingId") @ApiParam(value="팬미팅 id", required = true) Long meetingId) {
        logger.info("deleteMeeting 호출");

        SsafyUserDetails userDetails = (SsafyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());

        // 관리자 여부 확인
        if (user.getRole().equals("ROLE_USER")) {
            try {
                Meeting meeting = meetingRepository.findById(meetingId).get();
                // 현재 로그인된 회원 id와 삭제 요청한 팬미팅의 회원 id가 일치하는지 확인
                if (meeting.getUser().getId().equals(user.getId())) {
                    meetingService.deleteMeeting(meeting);
                    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("NO MANAGER", HttpStatus.NO_CONTENT);
                }
            } catch (Exception e) {
                return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("NO MANAGER", HttpStatus.BAD_REQUEST);
        }
    }

    // 기업회원 확인
    @GetMapping("/isManager")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공(SUCCESS)"),
            @ApiResponse(code = 400, message = "실패(Fail)"),
    })
    @ApiOperation(value = "기업회원 확인", notes = "기업회원 여부 확인")
    public ResponseEntity<String> checkManager() {
        logger.info("checkManager 호출");

        SsafyUserDetails userDetails = (SsafyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());

        // 관리자 여부 확인
        if (user.getRole().equals("ROLE_USER")) {
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }
}

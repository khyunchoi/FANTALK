package com.ssafy.api.controller;

import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{username}")
    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserRes> getUserInfo(@PathVariable("username") String username) {

        SsafyUserDetails userDetails = (SsafyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());

        User user = userService.getUserByUsername(username);

        UserRes res = new UserRes();
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setProfileImage(user.getProfileImage());

        return ResponseEntity.status(200).body(res);
    }

    @PutMapping(value = "/{username}")
    @ApiOperation(value = "회원 본인 정보 삭제", notes = "로그인한 회원 본인의 정보를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> remove(@PathVariable("username") String username) {

        SsafyUserDetails userDetails = (SsafyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username_sub = userDetails.getUsername(); // username_sub: 현재 로그인된 유저 sub 값

        if (username.equals(username_sub)) { // 현재 로그인된 유저와 삭제하려는 유저가 같으면 service 실행

            userService.removeUser(username);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401,"fail"));
    }
}

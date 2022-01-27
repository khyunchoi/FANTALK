package com.ssafy.common.oauth;

import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class SsafyOauth2UserService extends DefaultOAuth2UserService {

    private UserRepository userRepository;

    @Autowired
    public SsafyOauth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 구글로부터 받은 userRequest 데이터에 대해 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("getClientRegistration: "+userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인했는지 확인 가능
//        System.out.println("getAccessToken: "+userRequest.getAccessToken()); // AccessToken 확인 가능
//        System.out.println("getAttributes: "+super.loadUser(userRequest).getAttributes()); // 어떤 정보들을 받아오는지 확인 가능

        // 구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Client라이브러리) -> AccessToken요청
        // userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원프로필 받아준다
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 회원가입 진행
        String username = oAuth2User.getAttribute("sub");
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String profileImage = oAuth2User.getAttribute("picture");
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null) {
            userEntity = User.builder()
                    .username(username)
                    .name(name)
                    .email(email)
                    .profileImage(profileImage)
                    .role(role)
                    .build();

            userRepository.save(userEntity);
        }

        return new SsafyUserDetails(userEntity, oAuth2User.getAttributes());
    }
}

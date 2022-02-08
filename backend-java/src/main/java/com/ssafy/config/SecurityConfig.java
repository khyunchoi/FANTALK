package com.ssafy.config;

import com.ssafy.common.auth.SsafyUserDetailService;
import com.ssafy.common.oauth.SsafyOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 인증(authentication) 와 인가(authorization) 처리를 위한 스프링 시큐리티 설정 정의.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SsafyUserDetailService ssafyUserDetailService;
    private SsafyOauth2UserService ssafyOauth2UserService;

    @Autowired
    public SecurityConfig(SsafyUserDetailService ssafyUserDetailService, SsafyOauth2UserService ssafyOauth2UserService) {
        this.ssafyUserDetailService = ssafyUserDetailService;
        this.ssafyOauth2UserService = ssafyOauth2UserService;
    }

    // Password 인코딩 방식에 BCrypt 암호화 방식 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // DAO 기반으로 Authentication Provider를 생성
    // BCrypt Password Encoder와 UserDetailService 구현체를 설정
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.ssafyUserDetailService);
        return daoAuthenticationProvider;
    }

    // DAO 기반의 Authentication Provider가 적용되도록 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                    .disable()
                .csrf()
                    .disable()
                .formLogin()
                    .disable()
                .authorizeRequests() //인증이 필요한 URL과 필요하지 않은 URL에 대하여 설정
                    .antMatchers("/communities").authenticated()
                    .antMatchers("/meetings").access("hasRole('ROLE_MANAGER')")
                    .anyRequest().permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .and()
                .oauth2Login()
                    .loginPage("/oauth2/authorization/google") // 구글 로그인이 완료된 뒤의 후처리가 필요함. Tip. 코드X (엑세스토큰+사용자프로필정보)
                    .userInfoEndpoint()
                    .userService(ssafyOauth2UserService)
                .and().and().cors();
    }
}
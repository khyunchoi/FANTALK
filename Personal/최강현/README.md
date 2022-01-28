# Sub PJT 1

## 프로젝트 기획 브레인 스토밍

```
- 화상미팅화면
    - 화상카메라 영상송출
    - 카메라 on/off
    - 마이크 on/off
    - 채팅기능
    - 시간 제한 기능
    - 호스트가 임의로 시간을 조절할 수 있는지(1분정도)
- 대기화면
    - 영상재생
    - 광고송출(후순위)
    - 팬들끼리 놀고있을만한 미니게임(?)
- 팬미팅 신청 페이지
    - 선착순 / 랜덤 방식
- 개인 프로필 페이지
    - 신청 내역 확인
    - 신청 내역 변경 / 삭제
```



## UI 설계 회원관리

![image-20220113201952263](C:\Users\khyun\OneDrive\바탕 화면\toy_project\S06P12C105\Personal\최강현\README2.assets\image-20220113201952263.png)



## UI 설계 메인페이지 보강

![image-20220113202018516](C:\Users\khyun\OneDrive\바탕 화면\toy_project\S06P12C105\Personal\최강현\README2.assets\image-20220113202018516.png)



## ERD 설계

![image-20220113202347261](C:\Users\khyun\OneDrive\바탕 화면\toy_project\S06P12C105\Personal\최강현\README2.assets\image-20220113202347261.png)



## JPA 학습

### JPA에서 가장 중요한 2가지

- 객체와 관계형 데이터베이스 매핑하기
- 영속성 컨텍스트



### 영속성 컨텍스트

- JPA를 이해하는데 가장 중요한 용어
- 엔티티를 영구 저장하는 환경이라는 뜻
- EntityManager.persist(entity);
- 영속성 컨텍스트는 논리적인 개념
- 눈에 보이지 않는다.
- 엔티티 매니저를 통해서 영속성 컨텍스트에 접근



### 엔티티의 생명주기

- 비영속
  - 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
- 영속
  - 영속성 컨텍스트에 관리되는 상태
- 준영속
  - 영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제
  - 삭제된 상태

![image-20220113225406378](C:\Users\khyun\OneDrive\바탕 화면\toy_project\S06P12C105\Personal\최강현\README2.assets\image-20220113225406378.png)





### 영속성 컨텍스트의 이점

- 1차 캐시
- 동일성 보장
- 트랜잭션을 지원하는 쓰기 지연
- 변경 감지
- 지연 로딩



### 플러시

- 영속성 컨텍스트의 변경내용을 데이터베이스에 반영



### 플러시 발생

- 변경 감지
- 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
- 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송(등록, 수정, 삭제 쿼리)



### 영속성 컨텍스트를 플러시하는 방법

- em.flush() - 직접 호출
- 트랜잭션 커밋 - 플러시 자동 호출
- JPQL 쿼리 실행 - 플러시 자동 호출



### 플러시 모드 옵션

- FlushModeType.AUTO - 커밋이나 쿼리를 실행할 때 플러시
- FlushModeType.COMMIT - 커밋할 때만 플러시



### 플러시는

- 영속성 컨텍스트를 비우지 않음
- 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
- 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨



### 준영속 상태

- 영속 -> 준영속
- 영속 상태의 엔티티가 영속성 컨텍스트에서 분리
- 영속성 컨텍스트가 제공하는 기능을 사용 못함



### 준영속 상태로 만드는 방법

- em.detach(entity) - 특정 엔티티만 준영속 상태로 전환
- em.clear() - 영속성 컨텍스트를 완전히 초기화
- em.close() - 영속성 컨텍스트를 종료



# Sub PJT 2

## 엔티티 짜는 법

## id

```java
// Member 클래스 예시

@Id @GeneratedValue
@Column(name = "member_id")
private Long id;
```



## XtoOne

```java
// XtoOne은 무지성 LAZY 설정, XtoMany는 기본값이 LAZY

@ManyToOne(fetch = LAZY)
@JoinColumn(name = "member_id") // 외래키 지정
private Member member;

@OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "delivery_id")
private Delivery delivery;
```



## OnetoMany

```java
// ManytoMany는 왠만하면 쓰지말자
// XtoMany에 mappedBy (XtoOne 쪽이 주인)
// OnetoOne에 경우 둘 중 하나 잘 설정 

@OneToMany(mappedBy = "member") // 두 관계의 주인 설정
private List<Order> orders = new ArrayList<>();

@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> orderItems = new ArrayList<>();
```



## DateTime

```java
private LocalDateTime orderDate; //주문시간
```



## EnumType

```java
@Enumerated(EnumType.STRING) // 왠만하면 STRING (ORDINAL 절대금지)
private OrderStatus status; //주문상태 [ORDER, CANCEL]

public enum OrderStatus {
    ORDER, CANCEL
}
```



## Embedded

```java
@Embedded
private Address address;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // 기본생성자 protected로 막기
    }

    public Address(String city, String street, String zipcode) {

        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
```



# mobaxterm

## nginx



- nginx default 설정

```
sudo vi /etc/nginx/site-available/default/
```

- 현재 jar의 PID 확인

```
ps -ef | grep <jar-name>.jar
```

- Nginx 재시작

```
sudo service nginx restart
```

- PID 종료

```
kill -9 <PID>
```

- 백그라운드 실행 명령어

```
nohup java -jar <jar-name>.jar &
```



# SecurityConfig

```java
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
                    .antMatchers("/users").authenticated()
                    .antMatchers("/meeting/apply").access("hasRole('ROLE_MANAGER')")
                    .anyRequest().permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/logout-success")
                    .and()
                .oauth2Login()
                    .loginPage("/oauth2/authorization/google") // 구글 로그인이 완료된 뒤의 후처리가 필요함. Tip. 코드X (엑세스토큰+사용자프로필정보)
                    .userInfoEndpoint()
                    .userService(ssafyOauth2UserService);
    }
}
```



# SsafyUserDetails

```java
package com.ssafy.common.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.db.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 부가 상세정보(활성화 여부, 만료, 롤 등) 정의.
 */
public class SsafyUserDetails implements UserDetails, OAuth2User {

	private User user;
	private Map<String, Object> attributes;

	boolean accountNonExpired = true;
    boolean accountNonLocked = true;
    boolean credentialNonExpired = true;
    boolean enabled = true;

	//일반 로그인
    public SsafyUserDetails(User user) {
    		super();
    		this.user = user;
    }

	// OAuth 로그인
	public SsafyUserDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
    
    public User getUser() {
    		return this.user;
    }

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	// 해당 User의 권한을 리턴하는 곳!!
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
		collet.add(()->{ return user.getRole();});
		return collet;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getName() {
		return null;
	}
}

```



# SsafyUserDetailService

```java
package com.ssafy.common.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import org.springframework.stereotype.Service;


/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */
@Service
public class SsafyUserDetailService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public SsafyUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// 시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			return new SsafyUserDetails(userEntity);
		}
		return null;
	}
}

```


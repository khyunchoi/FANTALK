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

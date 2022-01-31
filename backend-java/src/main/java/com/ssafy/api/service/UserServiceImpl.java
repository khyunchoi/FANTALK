package com.ssafy.api.service;

import com.ssafy.db.repository.UserRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserRepositorySupport userRepositorySupport;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRepositorySupport userRepositorySupport) {
        this.userRepository = userRepository;
        this.userRepositorySupport = userRepositorySupport;
    }

    @Override
    public User getUserByUsername(String username) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회).
        User user = userRepositorySupport.findUserByUsername(username).get();
        return user;
    }

    @Override
    public void removeUser(String username) {
        // 유저 정보 삭제 (username을 통한 삭제)
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);

    }
}
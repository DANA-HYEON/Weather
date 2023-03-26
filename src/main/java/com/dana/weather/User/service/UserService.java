package com.dana.weather.User.service;

import com.dana.weather.User.domain.User;
import com.dana.weather.User.dto.Response;
import com.dana.weather.User.dto.UserLogin;
import com.dana.weather.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //회원가입
    public User save(String user_id, String password, String name){
        User user = userRepository.save(new User(user_id, password, name));
        return user;
    }

    //로그인
    public ResponseEntity login(UserLogin userLogin){
        Optional<User> user = userRepository.findById(userLogin.getId()); //입력한 아이디에 맞는 계정 찾기
        boolean isMatch;
        if(user.isPresent()){
           isMatch = passwordEncoder.matches(userLogin.getPassword(), user.get().getPassword()); //아이디가 있다면 비밀번호 체크
        } else {
            throw new IllegalArgumentException("아이디를 다시 확인해주세요.");
        }
        if (!isMatch){
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }

        return ResponseEntity.ok().body(new Response(200, "success"));
    }
}

package com.dana.weather;

import com.dana.weather.User.dto.UserLogin;
import com.dana.weather.User.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class WeatherApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Test
	void contextLoads() {
//        //test계정 생성
		userService.save("user_test", encoder.encode("test0000!"), "기상청유저1");


		//로그인 테스트
		userService.login(new UserLogin("user_test", "test0000!"));
	}
}

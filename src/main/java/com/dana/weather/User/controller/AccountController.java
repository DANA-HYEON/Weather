package com.dana.weather.User.controller;

import com.dana.weather.User.domain.User;
import com.dana.weather.User.dto.Response;
import com.dana.weather.User.dto.UserLogin;
import com.dana.weather.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLogin userLogin){
        try {
            return userService.login(userLogin);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Response(400, e.getMessage()));
        }
    }
}

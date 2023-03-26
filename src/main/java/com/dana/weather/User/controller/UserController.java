package com.dana.weather.User.controller;

import com.dana.weather.User.dto.Response;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/test")
    public ResponseEntity temp(){
        return ResponseEntity.ok("success");
    }
}

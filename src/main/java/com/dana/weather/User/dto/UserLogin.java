package com.dana.weather.User.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLogin {
    private String id; //유저 아이디

    private String password; //유저 비밀번호

    public UserLogin(String id, String password){
        this.id = id;
        this.password = password;
    }
}

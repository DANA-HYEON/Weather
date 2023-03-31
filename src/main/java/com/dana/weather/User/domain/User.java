package com.dana.weather.User.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
public class User {
    @Id
    private String id; //회원아이디

    @NotNull
    private String name; //회원이름

    @NotNull
    private String password; //회원비밀번호

    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public String getPassword(){
        return password;
    }
}

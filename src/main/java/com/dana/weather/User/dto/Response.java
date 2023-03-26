package com.dana.weather.User.dto;


import lombok.Getter;

@Getter
public class Response {

    private int code; //상태코트

    private String message; //응답 메시지

    public Response(int code, String message){
        this.code = code;
        this.message = message;
    }
}

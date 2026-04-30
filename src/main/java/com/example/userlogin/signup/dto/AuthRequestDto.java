package com.example.userlogin.signup.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String email;
    private String password;
}

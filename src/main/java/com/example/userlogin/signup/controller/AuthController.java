package com.example.userlogin.signup.controller;

import com.example.userlogin.signup.dto.AuthRequestDto;
import com.example.userlogin.signup.dto.AuthResponseDto;
import com.example.userlogin.signup.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    //SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequestDto dto){

        System.out.println("Signup api hit");

        return ResponseEntity.ok(service.register(dto));
    }

    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login (@RequestBody AuthRequestDto dto){

        String token = service.login(dto);

        return ResponseEntity.ok(new AuthResponseDto(token));
    }

}

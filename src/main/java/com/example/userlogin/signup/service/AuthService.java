package com.example.userlogin.signup.service;

import com.example.userlogin.signup.dto.AuthRequestDto;
import com.example.userlogin.signup.dto.AuthResponseDto;
import com.example.userlogin.signup.entity.User;
import com.example.userlogin.signup.jwtutility.JwtUtil;
import com.example.userlogin.signup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository repo;

    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;


    public @Nullable String register(AuthRequestDto dto) {

        User user = new User();
        user.setEmail(dto.getEmail());

        user.setPassword(encoder.encode(dto.getPassword()));
        repo.save(user);

        return "User registered";
    }

    //LOGIN
    public String login(AuthRequestDto dto) {

        User user = repo.findByEmail(dto.getEmail());

        if (user == null ||!encoder.matches(dto.getPassword(), user.getPassword())){

            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());

    }
}

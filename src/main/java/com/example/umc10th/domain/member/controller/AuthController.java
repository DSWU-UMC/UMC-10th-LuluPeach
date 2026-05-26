package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.*;
import com.example.umc10th.domain.member.service.AuthService;
import com.example.umc10th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;
    private final AuthService authService;

    // 로그아웃
    @PostMapping("/logout")
    public MemberResDTO.LogoutResultDTO logout() {
        return memberService.logout();
    }

    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberSignupResponse signup(
            @RequestBody MemberSignupRequest request
    ) {
        return memberService.signup(request);
    }


    // 로그인
    @PostMapping("/login")
    public MemberLoginResponse login(
            @RequestBody MemberLoginRequest request
    ) {
        return authService.login(request);
    }

}

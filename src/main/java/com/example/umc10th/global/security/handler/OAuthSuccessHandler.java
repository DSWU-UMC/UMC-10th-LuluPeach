package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.security.entity.OAuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import com.example.umc10th.global.security.entity.AuthMember;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        System.out.println("OAuth 로그인 성공 핸들러 실행됨");

        OAuthMember oAuthMember =
                (OAuthMember) authentication.getPrincipal();

        String accessToken =
                jwtUtil.createAccessToken(
                        new AuthMember(oAuthMember.getMember())
                );

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(accessToken);
    }
}
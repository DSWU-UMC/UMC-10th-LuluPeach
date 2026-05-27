package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.MemberLoginRequest;
import com.example.umc10th.domain.member.dto.MemberLoginResponse;

public interface AuthService {
    MemberLoginResponse login(MemberLoginRequest request);
}

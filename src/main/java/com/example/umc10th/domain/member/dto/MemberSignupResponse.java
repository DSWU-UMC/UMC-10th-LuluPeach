package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignupResponse {

    private Long memberId;

    private String name;

    private String email;
}

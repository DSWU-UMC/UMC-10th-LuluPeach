package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class MemberSignupRequest {

    private String name;

    private Gender gender;

    private LocalDate birth;

    private String address;

    private String email;

    private String password;

    private String phoneNum;

    private String profileUrl;

    // 선호 음식 ID 목록
    private List<Long> foodIds;
}

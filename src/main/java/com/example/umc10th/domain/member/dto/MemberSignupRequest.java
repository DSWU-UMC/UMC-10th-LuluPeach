package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
}

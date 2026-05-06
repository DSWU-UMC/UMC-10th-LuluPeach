package com.example.umc10th.domain.member.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Getter
    public static class SignupDTO {

        private String name;

        private String gender;

        private LocalDate birth;

        private String address;

        private String email;

        private String password;

        private String phoneNum;

        private String profileUrl;

        private List<Long> foodIds;
    }

    @Getter
    public static class UpdateDTO {

        private String name;

        private String address;

        private String phoneNum;

        private String profileUrl;

        private List<Long> foodIds;
    }

    @Getter
    public static class DeleteDTO {

        private Long memberId;

        private String password;
    }

}

package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignupResultDTO {

        private Long memberId;

        private String message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PointDTO {

        private Integer point;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UpdateResultDTO {

        private Long memberId;

        private String message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LogoutResultDTO {

        private String message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class DeleteResultDTO {

        private Long memberId;

        private String message;
    }

}

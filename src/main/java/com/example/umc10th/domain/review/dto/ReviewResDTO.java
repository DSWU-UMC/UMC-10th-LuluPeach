package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateResultDTO {

        private Long reviewId;

        private String message;
    }
}


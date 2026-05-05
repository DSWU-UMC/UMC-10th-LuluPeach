package com.example.umc10th.domain.review.dto;

import lombok.Getter;

import java.util.List;

public class ReviewReqDTO {

    @Getter
    public static class CreateDTO {

        private Long memberId;

        private Long storeId;

        private String content;

        private Float star;

        private List<String> photoUrls;
    }
}


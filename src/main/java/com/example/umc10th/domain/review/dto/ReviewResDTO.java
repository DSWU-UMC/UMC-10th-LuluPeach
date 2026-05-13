package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateResultDTO {

        private Long reviewId;

        private String message;
    }


    // 내가 쓴 리뷰 조회
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyReviewDTO {
        private Long reviewId;
        private Long storeId;
        private String storeName;
        private String content;
        private Float star;
        private LocalDateTime createdAt;
    }


    // 페이지네이션 툴 (Slice 기반)
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}

